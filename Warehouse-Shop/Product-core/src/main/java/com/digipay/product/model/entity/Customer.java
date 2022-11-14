package com.digipay.product.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_CUSTOMER")
public class Customer {
    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonIgnore
    private String customerId;
    @Column(name = "NATIONAL_ID", nullable = false, updatable = false, length = 10, unique = true)
    @Size(max = 10, min = 10, message = "National Id should be 10 digits.")
    @NotBlank(message = "National Id is mandatory.")
    private String nationalId;
    @Column(name = "FIRST_NAME")
    @NotBlank
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTH_DATE")
    @Past(message = "Birth date is wrong.")
    private LocalDate birthDate;
    @NotBlank(message = "Certificate No is mandatory.")
    private String birthCertificateNo;
    @NotBlank(message = "Birth place is mandatory.")
    private String birthPlace;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTACT_ID")
    private Contact contactInfo;


    private LocalDateTime registerDate;


    @PrePersist
    public void setRegisterDate() {
        this.registerDate = LocalDateTime.now();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCertificateNo() {
        return birthCertificateNo;
    }

    public void setBirthCertificateNo(String birthCertificateNo) {
        this.birthCertificateNo = birthCertificateNo;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Contact getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(Contact contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
}
