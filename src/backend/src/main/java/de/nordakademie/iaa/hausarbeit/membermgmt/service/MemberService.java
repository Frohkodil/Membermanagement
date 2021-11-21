package de.nordakademie.iaa.hausarbeit.membermgmt.service;


import de.nordakademie.iaa.hausarbeit.membermgmt.dao.MemberDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MemberSearchParameters;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {
    private MemberDAO memberDAO;

    public List<Member> searchMembers(MemberSearchParameters memberSearchParameters) {
        return memberDAO.search(memberSearchParameters);
    }

    public List<Member> listMembers() {
        return memberDAO.getAll();
    }

    public void createMember(Member member){
            memberDAO.save(member);
    }

    public void updateMember(Long id, Member changedMember) throws EntityNotFoundException {
        Member member = loadMember(id);
        member.setFirstName(changedMember.getFirstName());
        member.setLastName(changedMember.getLastName());
        member.setPostalCode(changedMember.getPostalCode());
        member.setCity(changedMember.getCity());
        member.setStreet(changedMember.getStreet());
        member.setStreetNumber(changedMember.getStreetNumber());
        member.setDateOfBirth(changedMember.getDateOfBirth());
        if(!member.getActiveMembership().equals(changedMember.getActiveMembership()))
            member.addMembership(changedMember.getActiveMembership());
        member.setIban(changedMember.getIban());
        member.setPaymentHistories(changedMember.getPaymentHistories());
        member.setFamilyMember(changedMember.getFamilyMember());
    }

    public Member loadMember(Long id) {
        return memberDAO.get(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteMember(Long id) throws MembershipStillActiveException {
        Member member = loadMember(id);
        if(member.getActiveMembership().getEndDate() == null || LocalDate.now().isBefore(member.getActiveMembership().getEndDate())) {
            throw new MembershipStillActiveException("Mitglied kann nicht gelöscht werden, da die Mitgliedschaft noch aktiv ist");
        }
        memberDAO.delete(member);
    }

    public List<Member> getActiveMembers() {
        return memberDAO.getActiveMembers();
    }

    @Inject
    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }



}
