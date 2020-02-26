package com.pdurasek.demo.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Audited
public class Book extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String ISBN;

    private String language;

    private int numberOfPages;

    private String publisher;

    private String genre;

    private int copies;

    private int copiesAvailable;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "book")
    private List<RentRecord> rentedUsers;

    public Book() {
        super();
    }

    public Book(String title, String ISBN, String language, int numberOfPages,
                String publisher, String genre, int copies, int copiesAvailable) {
        super();
        this.title = title;
        this.ISBN = ISBN;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.genre = genre;
        this.copies = copies;
        this.copiesAvailable = copiesAvailable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public List<RentRecord> getRentedUsers() {
        return rentedUsers;
    }

    public void setRentedUsers(List<RentRecord> rentedUsers) {
        this.rentedUsers = rentedUsers;
    }
}
