package com.pdurasek.demo.controller.v1;

import com.pdurasek.demo.dto.OCR.ScanDTO;
import com.pdurasek.demo.dto.UserAccountDTO;
import com.pdurasek.demo.model.UserAccount;
import com.pdurasek.demo.repository.UserAccountRepository;
import com.pdurasek.demo.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserAccountController {

    private final Logger log = LoggerFactory.getLogger(UserAccountController.class);

    @Autowired
    private UserAccountRepository userAccountRepository;

    private UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public ResponseEntity<List<UserAccountDTO>> getAllUsers() {
        log.debug("REST request to get all Users");

        List<UserAccountDTO> userAccountDTOS = userAccountService.findAllUserAccounts();

        if (userAccountDTOS != null) {
            return ResponseEntity.ok(userAccountDTOS);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountDTO> getUserById(@PathVariable int id) {
        log.debug("REST request to get User with ID: " + id);

        UserAccountDTO userAccount = userAccountService.findUserAccountById(id);

        if (userAccount != null) {
            return ResponseEntity.ok(userAccount);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<UserAccountDTO>> getOverdueUsers() {
        log.debug("REST request to get User with overdue");

        List<UserAccountDTO> userAccount = userAccountService.getOverdueUsers();

        if (userAccount != null) {
            return ResponseEntity.ok(userAccount);
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserAccountDTO> createUserAccount(@Valid @RequestBody UserAccountDTO userAccountDTO) throws URISyntaxException {
        log.debug("REST request to save user account: " + userAccountDTO);

        if (userAccountDTO.getId() != null) {
            return ResponseEntity.badRequest().body(null);
        }

        UserAccountDTO savedUserAccountDTO = userAccountService.createUserAccount(userAccountDTO);

        if (savedUserAccountDTO != null) {
            return ResponseEntity.created(new URI("/api/v1/users/" + savedUserAccountDTO.getId()))
                    .body(savedUserAccountDTO);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(path = "/ocr", consumes = "application/json")
    public ResponseEntity<UserAccountDTO> automaticUserEntry(@RequestBody ScanDTO scanDTO) throws URISyntaxException {

        if (scanDTO != null && scanDTO.getImageBase64() != null && scanDTO.getRecognizerType() != null) {
            UserAccountDTO userAccountDTO = null;

            userAccountDTO = userAccountService.getUserAccountFromOCR(scanDTO);

            if (userAccountDTO != null) {
                return ResponseEntity.created(new URI("/api/v1/users/" + userAccountDTO.getId()))
                        .body(userAccountDTO);
            } else {
                return ResponseEntity.badRequest().body(null);

            }
        }

        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping
    public ResponseEntity<UserAccountDTO> updateUserAccount(@Valid @RequestBody UserAccountDTO userAccountDTO) throws URISyntaxException {
        log.debug("REST request to update UserAccount: " + userAccountDTO);

        if (userAccountDTO.getId() == null) {
            return createUserAccount(userAccountDTO);
        }

        UserAccountDTO updatedUserAccountDTO = userAccountService.updateUserAccount(userAccountDTO);

        if (updatedUserAccountDTO != null) {
            return ResponseEntity.ok().body(updatedUserAccountDTO);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Integer id) {
        log.debug("REST request to delete User Account with ID: " + id);

        Optional<UserAccount> userAccount = userAccountRepository.findById(id);

        if (userAccount.isPresent()) {
            userAccountRepository.delete(userAccount.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
