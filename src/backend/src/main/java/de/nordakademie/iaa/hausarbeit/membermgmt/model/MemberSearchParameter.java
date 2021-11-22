package de.nordakademie.iaa.hausarbeit.membermgmt.model;


import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

/**
 *  MemberSearchParameter class with search parameters for Member
 *
 * @author Siebo Vogel
 */

public class MemberSearchParameter {
    private String firstName;
    private String lastName;
    private String city;
    private String postalCode;
    @Past
    private LocalDate dateOfBirth;
    private MembershipType membershipType;
    private Boolean openPayments;
    private Boolean active;


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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public Boolean getOpenPayments() {return openPayments;}

    public void setOpenPayments(Boolean openPayments) {this.openPayments = openPayments;}

    public Boolean getActive() {return active;}

    public void setActive(Boolean active) {this.active = active;}
}
