package com.transport.sabi.api.domain.driver;

import com.transport.sabi.api.domain.BaseEntity;
import com.transport.sabi.api.domain.driver.Driver;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PersonalInformation extends BaseEntity {
    private String nationality;
    private String religion;
    private String martialStatus;

    private String employmentOfSpouse;
    private int children;
    private String driverLicence;
    private String aadharNo;
    private String  whatsappNo;
    @OneToOne(cascade = CascadeType.ALL)
    private Driver driver;

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

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getWhatsappNo() {
        return whatsappNo;
    }

    public void setWhatsappNo(String whatsappNo) {
        this.whatsappNo = whatsappNo;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
