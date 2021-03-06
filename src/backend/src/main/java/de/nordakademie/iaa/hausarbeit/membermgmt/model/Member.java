package de.nordakademie.iaa.hausarbeit.membermgmt.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Member entity.
 *
 * @author Siebo Vogel
 */

@Entity
public class Member implements Serializable {

    private static final long serialVersionUID = -4187784448091433991L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Vorname ist obligatorisch")
    private String firstName;
    @NotBlank(message = "Nachname ist obligatorisch")
    private String lastName;

    @NotBlank(message = "Postleitzahl ist obligatorisch")
    @Size(min=5, max=5)
    private String postalCode ;

    @NotBlank(message = "Stadt ist obligatorisch")
    private String city;
    @NotBlank(message = "Straße ist obligatorisch")
    private String street;
    @NotBlank(message = "Hausnummer ist obligatorisch")
    @Pattern(regexp = "[0-9]{1,5}[a-z]{2}")
    private String streetNumber;
    @Column(nullable = false)
    @Past
    private LocalDate dateOfBirth;
    @NotBlank(message = "IBAN ist obligatorisch")
    @Size(min=22, max = 22)
    @Pattern(regexp = "[a-zA-Z]{2}[0-9]{2}[0-9]{4}[0-9]{7}([0-9]?){0,16}")
    private String iban;
    @OneToMany
    private List<PaymentHistory> paymentHistories;
    @OneToOne
    @Valid
    private Member familyMember;
    @OneToMany
    private List<Membership> memberships;

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

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void addMembership(Membership membership) {
        memberships.add(membership);
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getStreetNumber() { return streetNumber; }

    public void setStreetNumber(String streetNumber) { this.streetNumber = streetNumber; }

    public Membership getActiveMembership() {
        return memberships.get(memberships.size()-1);
    }
}
