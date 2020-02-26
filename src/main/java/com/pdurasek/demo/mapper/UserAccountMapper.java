package com.pdurasek.demo.mapper;

import com.pdurasek.demo.dto.UserAccountDTO;
import com.pdurasek.demo.model.UserAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAccountMapper {

    public static UserAccountDTO userToUserDTO(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = userToUserDTOWithoutReferences(userAccount);
        userAccountDTO.setRentedBooks(RentRecordMapper.rentRecordsToRentRecordDTOsWithBook(userAccount.getRentedBooks()));
        userAccountDTO.setUserContacts(UserContactMapper.userContactsToUserContactDTOsWithoutUser(userAccount.getUserContacts()));

        return userAccountDTO;
    }

    public static List<UserAccountDTO> usersToUserDTOs(List<UserAccount> userAccounts) {
        if (userAccounts == null) {
            return null;
        }

        List<UserAccountDTO> list = new ArrayList<>(userAccounts.size());
        for (UserAccount userAccount : userAccounts) {
            list.add(userToUserDTO(userAccount));
        }

        return list;
    }

    public static UserAccountDTO userToUserDTOWithoutReferences(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(userAccount.getId());
        userAccountDTO.setFirstName(userAccount.getFirstName());
        userAccountDTO.setLastName(userAccount.getLastName());
        userAccountDTO.setDateOfBirth(userAccount.getDateOfBirth());
        userAccountDTO.setValid(userAccount.isValid());

        return userAccountDTO;
    }

    public static List<UserAccountDTO> usersToUserDTOsWithoutReferences(List<UserAccount> userAccounts) {
        if (userAccounts == null) {
            return null;
        }

        List<UserAccountDTO> list = new ArrayList<>(userAccounts.size());
        for (UserAccount userAccount : userAccounts) {
            list.add(userToUserDTOWithoutReferences(userAccount));
        }

        return list;
    }

    public static UserAccount userAccountDTOToUserAccount(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = userAccountDTOToUserAccountWithoutReferences(userAccountDTO);
        userAccount.setUserContacts(UserContactMapper.userContactDTOsToUserContactsWithUser(userAccountDTO.getUserContacts()));

        return userAccount;
    }

    public static UserAccount userAccountDTOToUserAccountWithBookRef(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = userAccountDTOToUserAccountWithoutReferences(userAccountDTO);
        userAccount.setUserContacts(UserContactMapper.userContactDTOsToUserContactsWithoutUser(userAccountDTO.getUserContacts()));

        return userAccount;
    }

    public static UserAccount userAccountDTOToUserAccountWithoutReferences(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userAccountDTO.getId());
        userAccount.setFirstName(userAccountDTO.getFirstName());
        userAccount.setLastName(userAccountDTO.getLastName());
        userAccount.setDateOfBirth(userAccountDTO.getDateOfBirth());
        userAccount.setValid(userAccountDTO.isValid());

        return userAccount;
    }

}
