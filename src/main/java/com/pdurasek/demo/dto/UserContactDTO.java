package com.pdurasek.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pdurasek.demo.model.UserAccount;
import com.pdurasek.demo.model.enums.ContactType;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserContactDTO {

    private Integer id;

    private ContactType contactType;

    private String contact;

    private UserAccount userAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
