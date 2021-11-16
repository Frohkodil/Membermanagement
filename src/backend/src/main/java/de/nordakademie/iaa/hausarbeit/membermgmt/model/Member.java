package de.nordakademie.iaa.hausarbeit.membermgmt.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
public class Member implements Serializable {

    private static final long serialVersionUID = -4187784448091433991L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @NotNull
    @Size(min=5, max=5)
    private String postalCode ;


    private String city;
    private String street;
    private String streetNumber;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String iban;
    @OneToMany
    private List<PaymentHistory> paymentHistories;
    @OneToOne
    private Member familyMember;
    @OneToOne
    private Membership membership;

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public List<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }

    public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
        this.paymentHistories = paymentHistories;
    }

    public Member getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(Member familyMember) {
        this.familyMember = familyMember;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getStreetNumber() { return streetNumber; }

    public void setStreetNumber(String streetNumber) { this.streetNumber = streetNumber; }
}
