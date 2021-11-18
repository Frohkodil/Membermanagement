package de.nordakademie.iaa.hausarbeit.membermgmt.controller;


import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MemberSearchParameters;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.EntityAlreadyPresentException;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.MemberService;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.MembershipService;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.MembershipStillActiveException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping(path="/members")
public class MemberController {

    private MemberService memberService;
    private MembershipService membershipService;

    @RequestMapping(method = POST)
    public ResponseEntity<?> createMember(@RequestBody Member member, @RequestBody Membership membership) {
        try {
            memberService.createMember(member);
            membershipService.createMembership(membership);
            return ResponseEntity.status(CREATED).build();
        }
        catch (EntityAlreadyPresentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RequestMapping(path = "/{id}", method = PUT)
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestBody Member member) {
        try {
            memberService.updateMember(id, member.getFirstName(), member.getLastName(), member.getPostalCode(), member.getCity(), member.getStreet(), member.getStreetNumber(), member.getDateOfBirth(), member.getMembership(), member.getIban(), member.getPaymentHistories(), member.getFamilyMember());
            return ResponseEntity.status(CREATED).build();
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

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

    @RequestMapping(method = GET)
    public List<Member> listMembers() {
        return memberService.listMembers();
    }

    @RequestMapping(path = "/{id}", method = GET)
    public ResponseEntity<Member> loadMember(@PathVariable("id") Long id) {
        try {
            Member member = memberService.loadMember(id);
            return ResponseEntity.ok(member);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(path = "/search", method = POST)
    public ResponseEntity<List<Member>> searchMember(@RequestBody MemberSearchParameters memberSearchParameters) {
        List<Member> searchMembers = memberService.searchMembers(memberSearchParameters);
        if(searchMembers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(searchMembers);
        }
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
