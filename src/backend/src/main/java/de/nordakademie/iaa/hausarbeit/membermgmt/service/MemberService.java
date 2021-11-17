package de.nordakademie.iaa.hausarbeit.membermgmt.service;


import de.nordakademie.iaa.hausarbeit.membermgmt.dao.MemberDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class MemberService {
    private MemberDAO memberDAO;

    public List<Member> searchMembers(String searchBase64) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(searchBase64);
        String searchJSON = new String(decodedBytes);

        return null;
    }

    //todo das hier fixen
    @SuppressWarnings("unchecked")
    public List<Member> listMembers() {
        return memberDAO.getAll();
    }

    public void createMember(Member member) throws EntityAlreadyPresentException {
        //todo input validation
        try {
            memberDAO.save(member);
        }
        catch (ConstraintViolationException e) {
            throw new EntityAlreadyPresentException();
        }

    }
    public void updateMember(Long id, String firstName, String lastName, String postalCode, String city, String street, String streetNumber, Date dateOfBirth, Membership membership, String iban, List<PaymentHistory> paymentHistory, Member familyMember) throws EntityNotFoundException {
        Member member = loadMember(id);
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
        return memberDAO.get(id).orElseThrow(EntityNotFoundException::new);
    }

    //todo membershipstillactiveexception durch constraintviolationexception ersetzen ??
    public void deleteMember(Long id) throws MembershipStillActiveException {
        Member member = loadMember(id);
        if(member.getMembership() != null) {
            throw new MembershipStillActiveException();
        }
        memberDAO.delete(member);
    }

    @Inject
    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }
}
