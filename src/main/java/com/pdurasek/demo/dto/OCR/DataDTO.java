package com.pdurasek.demo.dto.OCR;

public class DataDTO {

    private String officialTravelDocument;

    private String issuingState;

    private String documentNumber;

    private int checkDocumentCodeDigit;

    private String optional1;

    private String birthDate;

    private int checkBirthDateDigit;

    private String sex;

    private String expiryDate;

    private int checkExpiryDateDigit;

    private String nationality;

    private String optional2;

    private int checkOverallDigit;

    private String primaryIdentifier;

    private String secondaryIdentifier;

    public String getOfficialTravelDocument() {
        return officialTravelDocument;
    }

    public void setOfficialTravelDocument(String officialTravelDocument) {
        this.officialTravelDocument = officialTravelDocument;
    }

    public String getIssuingState() {
        return issuingState;
    }

    public void setIssuingState(String issuingState) {
        this.issuingState = issuingState;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public int getCheckDocumentCodeDigit() {
        return checkDocumentCodeDigit;
    }

    public void setCheckDocumentCodeDigit(int checkDocumentCodeDigit) {
        this.checkDocumentCodeDigit = checkDocumentCodeDigit;
    }

    public String getOptional1() {
        return optional1;
    }

    public void setOptional1(String optional1) {
        this.optional1 = optional1;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getCheckBirthDateDigit() {
        return checkBirthDateDigit;
    }

    public void setCheckBirthDateDigit(int checkBirthDateDigit) {
        this.checkBirthDateDigit = checkBirthDateDigit;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCheckExpiryDateDigit() {
        return checkExpiryDateDigit;
    }

    public void setCheckExpiryDateDigit(int checkExpiryDateDigit) {
        this.checkExpiryDateDigit = checkExpiryDateDigit;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOptional2() {
        return optional2;
    }

    public void setOptional2(String optional2) {
        this.optional2 = optional2;
    }

    public int getCheckOverallDigit() {
        return checkOverallDigit;
    }

    public void setCheckOverallDigit(int checkOverallDigit) {
        this.checkOverallDigit = checkOverallDigit;
    }

    public String getPrimaryIdentifier() {
        return primaryIdentifier;
    }

    public void setPrimaryIdentifier(String primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }

    public String getSecondaryIdentifier() {
        return secondaryIdentifier;
    }

    public void setSecondaryIdentifier(String secondaryIdentifier) {
        this.secondaryIdentifier = secondaryIdentifier;
    }
}
