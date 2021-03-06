package de.nordakademie.iaa.hausarbeit.membermgmt.controller;


import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MemberSearchParameter;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * REST controller for Member entity.
 *
 * @author Siebo Vogel
 */

@RestController
@RequestMapping(path="/members")
public class MemberController {

    private MemberService memberService;
    private MembershipService membershipService;


    /**
     * Creates a new Member with a membership.
     * @param member Member to be created
     * @param membership Membership for the newly created member
     * @return a response entity.
     */
    @RequestMapping(method = POST)
    public ResponseEntity<?> createMember(@Valid @RequestBody Member member, @Valid @RequestBody Membership membership) {
        try {
            membershipService.createMembership(membership);
            member.addMembership(membership);
            memberService.createMember(member);
            return ResponseEntity.status(CREATED).build();
        } catch (IllegalDateException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Updates a Member.
     * @param member Member to be updated
     * @param id ID of the Member to be updated
     * @return a response entity.
     */
    @RequestMapping(path = "/{id}", method = PUT)
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @Valid @RequestBody Member member) {
        try {
            memberService.updateMember(id, member);
            return ResponseEntity.status(CREATED).build();
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Deletes a Member.
     * @param id ID of the Member to be deleted
     * @return a response entity.
     */
    @RequestMapping(path = "/{id}", method = DELETE)
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.ok().build();
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (MembershipStillActiveException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Lists all Members.
     * @return a List of Members.
     */
    @RequestMapping(method = GET)
    public List<Member> listMembers() {
        return memberService.listMembers();
    }

    /**
     * Gets a Member.
     * @param id ID of the Member to get
     * @return a Member.
     */
    @RequestMapping(path = "/{id}", method = GET)
    public ResponseEntity<Member> loadMember(@PathVariable("id") Long id) {
        try {
            Member member = memberService.loadMember(id);
            return ResponseEntity.ok(member);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Searches Members.
     * @param memberSearchParameter Serach Parameters for the search
     * @return a List of Members.
     */
    @RequestMapping(path = "/search", method = POST)
    public ResponseEntity<List<Member>> searchMember(@Valid @RequestBody MemberSearchParameter memberSearchParameter) {
        List<Member> searchMembers = memberService.searchMembers(memberSearchParameter);
        if(searchMembers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(searchMembers);
    }

    /**
     * Lists active Members.
     * @return a List of Members.
     */
    @RequestMapping(path = "/active", method = GET)
    public ResponseEntity<List<Member>> listActiveMembers() {
        List<Member> activeMembers = memberService.getActiveMembers();
        if(activeMembers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(activeMembers);
    }


    @Inject
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Inject
    public void setMembershipService(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

}
