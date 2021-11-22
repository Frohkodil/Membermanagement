package de.nordakademie.iaa.hausarbeit.membermgmt.service;


import de.nordakademie.iaa.hausarbeit.membermgmt.dao.MemberDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MemberSearchParameter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Siebo Vogel
 */
@Service
public class MemberService {
    private MemberDAO memberDAO;

    /**
     * List all members fitting the search parameters.
     *
     * @param memberSearchParameter The parameters for the search.
     * @return a list of Member entities. If no member was found an empty list is
     * returned.
     */
    public List<Member> searchMembers(MemberSearchParameter memberSearchParameter) {
        return memberDAO.search(memberSearchParameter);
    }

    /**
     * List all members currently stored in the database.
     *
     * @return a list of Member entities. If no member was found an empty list is
     * returned.
     */
    public List<Member> listMembers() {
        return memberDAO.getAll();
    }

    /**
     * Creates a new member entity.
     *
     * @param member The member to be created.
     */
    public void createMember(Member member){
            memberDAO.save(member);
    }

    /**
     * Updates a member entity and stores the changes into the database.
     *
     * @param id       The identifier.
     * @param changedMember The updated member.
     * @throws EntityNotFoundException if no course could be found for the given id.
     */
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

    /**
     * Loads a member identified by an id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public Member loadMember(Long id) {
        return memberDAO.get(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Deletes the member with the given id.
     *
     * @param id The identifier.
     * @throws EntityNotFoundException if no member could be found for the given id.
     * @throws MembershipStillActiveException if the member couldn't be deleted because there is still an acitve membershhip.
     */
    public void deleteMember(Long id) throws MembershipStillActiveException, EntityNotFoundException{
        Member member = loadMember(id);
        if(member.getActiveMembership().getEndDate() == null || LocalDate.now().isBefore(member.getActiveMembership().getEndDate())) {
            throw new MembershipStillActiveException("Mitglied kann nicht gelöscht werden, da die Mitgliedschaft noch aktiv ist");
        }
        memberDAO.delete(member);
    }

    /**
     * Lists the members with active memberships.
     *
     * @return a list of Member entities. If no member was found an empty list is
     * returned.
     */
    public List<Member> getActiveMembers() {
        return memberDAO.getActiveMembers();
    }

    @Inject
    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }



}
