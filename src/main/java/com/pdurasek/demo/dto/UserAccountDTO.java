package com.pdurasek.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserAccountDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private boolean isValid;

    private List<RentRecordDTO> rentedBooks;

    private List<UserContactDTO> userContacts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<RentRecordDTO> getRentedBooks() {
        return rentedBooks;
    }

    public void setRentedBooks(List<RentRecordDTO> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    public List<UserContactDTO> getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(List<UserContactDTO> userContacts) {
        this.userContacts = userContacts;
    }
}
