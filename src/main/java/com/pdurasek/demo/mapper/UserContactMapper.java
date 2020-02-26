package com.pdurasek.demo.mapper;

import com.pdurasek.demo.dto.UserContactDTO;
import com.pdurasek.demo.model.UserContact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserContactMapper {

    public static UserContactDTO userContactToUserContactDTOWithoutUser(UserContact userContact) {
        UserContactDTO userContactDTO = new UserContactDTO();
        userContactDTO.setId(userContact.getId());
        userContactDTO.setContact(userContact.getContact());
        userContactDTO.setContactType(userContact.getContactType());

        return userContactDTO;
    }

    public static List<UserContactDTO> userContactsToUserContactDTOsWithoutUser(List<UserContact> userContacts) {
        if (userContacts == null) {
            return null;
        }

        List<UserContactDTO> list = new ArrayList<>(userContacts.size());
        for (UserContact userContact : userContacts) {
            list.add(userContactToUserContactDTOWithoutUser(userContact));
        }

        return list;
    }

    public static UserContact userContactDTOToUserContactWithoutReferences(UserContactDTO userContactDTO) {
        UserContact userContact = new UserContact();
        userContact.setId(userContactDTO.getId());
        userContact.setContact(userContactDTO.getContact());
        userContact.setContactType(userContactDTO.getContactType());

        return userContact;
    }

    public static List<UserContact> userContactDTOsToUserContactsWithoutUser(List<UserContactDTO> userContactDTOS) {
        if (userContactDTOS == null) {
            return null;
        }

        List<UserContact> list = new ArrayList<>(userContactDTOS.size());
        for (UserContactDTO userContact : userContactDTOS) {
            list.add(userContactDTOToUserContactWithoutReferences(userContact));
        }

        return list;
    }

    public static UserContact userContactDTOToUserContactWithUser(UserContactDTO userContactDTO) {
        UserContact userContact = userContactDTOToUserContactWithoutReferences(userContactDTO);
        userContact.setUserAccount(userContactDTO.getUserAccount());

        return userContact;
    }

    public static List<UserContact> userContactDTOsToUserContactsWithUser(List<UserContactDTO> userContactDTOS) {
        if (userContactDTOS == null) {
            return null;
        }

        List<UserContact> list = new ArrayList<>(userContactDTOS.size());
        for (UserContactDTO userContact : userContactDTOS) {
            list.add(userContactDTOToUserContactWithUser(userContact));
        }

        return list;
    }
}
