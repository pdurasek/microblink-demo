package com.pdurasek.demo.service.impl;

import com.pdurasek.demo.dto.OCR.DataDTO;
import com.pdurasek.demo.dto.OCR.ScanDTO;
import com.pdurasek.demo.dto.UserAccountDTO;
import com.pdurasek.demo.mapper.UserAccountMapper;
import com.pdurasek.demo.model.UserAccount;
import com.pdurasek.demo.repository.UserAccountRepository;
import com.pdurasek.demo.service.UserAccountService;
import com.pdurasek.demo.util.OcrParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final Logger log = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    private UserAccountRepository userAccountRepository;

    @Value("${MICROBLINK_BEARER_TOKEN}")
    private String bearerToken;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccountDTO getUserAccountFromOCR(ScanDTO scanDTO) {
        String scanUrl = "https://api.microblink.com/recognize/execute";
        // Construct request to for Microblink OCR Api
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + bearerToken);

        Map<String, String> params = new HashMap<>();
        params.put("recognizerType", scanDTO.getRecognizerType());
        params.put("imageBase64", scanDTO.getImageBase64());

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(scanUrl, entity, String.class);

        if (result.getStatusCode() == HttpStatus.OK) {
            try {
                if(result.getBody() == null) {
                    return null;
                }

                // Extract the rawMRZString that is required to validate and populate user data
                String rawMRZString = "rawMRZString\":\"";
                int start = result.getBody().indexOf(rawMRZString) + rawMRZString.length();
                int end = result.getBody().indexOf("\"", start);
                String rawData = result.getBody().substring(start, end);

                String[] rows = rawData.split("\\\\n");
                // Parse each row and populate temporary data object
                DataDTO userData = new DataDTO();
                OcrParseUtil.analyzeFirstRow(rows[0], userData);
                OcrParseUtil.analyzeSecondRow(rows[1], userData);
                OcrParseUtil.analyzeThirdRow(rows[2], userData);

                boolean isValid = OcrParseUtil.validateUser(userData);

                LocalDate birthDate = LocalDate.parse(userData.getBirthDate(), DateTimeFormatter.ofPattern("yyMMdd"));
                UserAccount userAccount = new UserAccount(userData.getPrimaryIdentifier(), userData.getSecondaryIdentifier(), birthDate, isValid);
                userAccountRepository.save(userAccount);

                return UserAccountMapper.userToUserDTO(userAccount);
            } catch (IndexOutOfBoundsException e) {
                log.error("Error while trying to get user data from OCR", e.getCause());
            }
        }

        return null;
    }

    @Override
    public List<UserAccountDTO> getOverdueUsers() {
        List<UserAccount> userAccounts = userAccountRepository.findByOverdue();

        userAccounts.forEach(userAccount -> userAccount.getRentedBooks()
                .removeIf(rentRecord -> rentRecord.getOverdueDays() == 0 || rentRecord.getReturnedDate() != null));

        return UserAccountMapper.usersToUserDTOs(userAccounts);
    }

    @Override
    public List<UserAccountDTO> findAllUserAccounts() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();

        return UserAccountMapper.usersToUserDTOs(userAccounts);
    }

    @Override
    public UserAccountDTO findUserAccountById(int id) {
        UserAccount userAccount = userAccountRepository.findById(id);

        return UserAccountMapper.userToUserDTO(userAccount);
    }

    @Override
    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = UserAccountMapper.userAccountDTOToUserAccount(userAccountDTO);
        userAccount.getUserContacts().forEach(userContact -> userContact.setUserAccount(userAccount));

        userAccountRepository.save(userAccount);

        return UserAccountMapper.userToUserDTO(userAccount);
    }

    @Override
    public UserAccountDTO updateUserAccount(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = UserAccountMapper.userAccountDTOToUserAccount(userAccountDTO);
        userAccount.getUserContacts().forEach(userContact -> userContact.setUserAccount(userAccount));

        return UserAccountMapper.userToUserDTO(userAccountRepository.save(userAccount));
    }

}
