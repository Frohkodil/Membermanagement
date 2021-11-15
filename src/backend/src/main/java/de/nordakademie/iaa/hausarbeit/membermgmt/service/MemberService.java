package de.nordakademie.iaa.hausarbeit.membermgmt.service;


import de.nordakademie.iaa.hausarbeit.membermgmt.dao.MemberDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;


public class MemberService {
    private MemberDAO memberDAO;

    //todo das hier fixen
    @SuppressWarnings("unchecked")
    public List<Member> listMembers() {
        return memberDAO.listMembers();
    }

    public void createMember(Member member) throws EntityAlreadyPresentException {
        //todo input validation
        try {
            memberDAO.persistMember(member);
        }
        catch (ConstraintViolationException e) {
            throw new EntityAlreadyPresentException();
        }

    }
    public void updateMember(Long id, String firstName, String lastName, String postalCode, String city, String street, String streetNumber, LocalDate dateOfBirth, Membership membership, String iban, List<PaymentHistory> paymentHistory, Member familyMember) throws EntityNotFoundException {
        Member member = loadMember(id);
        if(member == null) {
            throw new EntityNotFoundException();
        }
        //todo input validation
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setPostalCode(postalCode);
        member.setCity(city);
        member.setStreet(street);
        member.setStreetNumber(streetNumber);
        member.setDateOfBirth(dateOfBirth);
        member.setMembership(membership);
        member.setIban(iban);
        member.setPaymentHistories(paymentHistory);
        member.setFamilyMember(familyMember);
    }

    public Member loadMember(Long id) {
          Member member = memberDAO.loadMember(id);
          if (member == null) {
                  throw new EntityNotFoundException();
          } else return member;
    }

    //todo membershipstillactiveexception durch constraintviolationexception ersetzen ??
    public void deleteMember(Long id) throws MembershipStillActiveException {
        Member member = loadMember(id);
        if(member.getMembership() != null) {
            throw new MembershipStillActiveException();
        } else {
            memberDAO.deleteMember(member);
        }
    }

    @Inject
    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }
}
