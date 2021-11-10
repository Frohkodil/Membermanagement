package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Member {
    private Long id;
    private String firstName;
    private String lastName;
    private String postalCode ;
    private String street;
    private LocalDate dateOfBirth;
    private String iban;
    private List<PaymentHistory> paymentHistories;
    private Member familyMember;
    private Membership membership;

    public Member(String firstName, String lastName, String postalCode, String street, LocalDate dateOfBirth, String iban, List<PaymentHistory> paymentHistories, Member familyMember, Membership membership) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
        this.street = street;
        this.dateOfBirth = dateOfBirth;
        this.iban = iban;
        this.paymentHistories = paymentHistories;
        this.familyMember = familyMember;
        this.membership = membership;
    }

    @Id
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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
}
