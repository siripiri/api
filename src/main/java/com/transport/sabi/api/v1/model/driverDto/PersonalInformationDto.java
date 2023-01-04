package com.transport.sabi.api.v1.model.driverDto;

public class PersonalInformationDto {
    private Long id;
    private String nationality;
    private String religion;
    private String martialStatus;
    private String employmentOfSpouse;
    private int children;
    private String driverLicence;
    private String aadharCard;
    private String  whatsappNo;
    private Long diverId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }

    public String getEmploymentOfSpouse() {
        return employmentOfSpouse;
    }

    public void setEmploymentOfSpouse(String employmentOfSpouse) {
        this.employmentOfSpouse = employmentOfSpouse;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getDriverLicence() {
        return driverLicence;
    }

    public void setDriverLicence(String driverLicence) {
        this.driverLicence = driverLicence;
    }

    public String getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(String aadharCard) {
        this.aadharCard = aadharCard;
    }

    public String getWhatsappNo() {
        return whatsappNo;
    }

    public void setWhatsappNo(String whatsappNo) {
        this.whatsappNo = whatsappNo;
    }

    public Long getDiverId() {
        return diverId;
    }

    public void setDiverId(Long diverId) {
        this.diverId = diverId;
    }
}
