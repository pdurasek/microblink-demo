package com.pdurasek.demo.model;

import com.pdurasek.demo.model.enums.ContactType;
import com.pdurasek.demo.model.enums.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table
@Audited
@TypeDef(name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class)
public class UserContact extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "user_contact_id_seq",
            sequenceName = "user_contact_id_seq",
            allocationSize = 1)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "contact")
    @Type(type = "pgsql_enum")
    private ContactType contactType;

    private String contact;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    public UserContact() {
        super();
    }

    public UserContact(ContactType contactType, String contact, UserAccount userAccount) {
        super();
        this.contactType = contactType;
        this.contact = contact;
        this.userAccount = userAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
