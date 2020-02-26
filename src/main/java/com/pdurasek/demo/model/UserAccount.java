package com.pdurasek.demo.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Audited
public class UserAccount extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private boolean isValid;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "userAccount")
    private List<RentRecord> rentedBooks;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "userAccount",
            orphanRemoval = true)
    private List<UserContact> userContacts;

    public UserAccount() {
        super();
    }

    public UserAccount(String firstName, String lastName, LocalDate dateOfBirth, boolean isValid) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isValid = isValid;
    }

    public UserAccount(String firstName, String lastName, LocalDate dateOfBirth, boolean isValid, List<UserContact> userContacts) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isValid = isValid;
        this.userContacts = userContacts;
    }

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

    public List<RentRecord> getRentedBooks() {
        return rentedBooks;
    }

    public void setRentedBooks(List<RentRecord> rentedBooks) {
        this.rentedBooks = rentedBooks;
    }

    public List<UserContact> getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(List<UserContact> userContacts) {
        this.userContacts = userContacts;
    }
}
