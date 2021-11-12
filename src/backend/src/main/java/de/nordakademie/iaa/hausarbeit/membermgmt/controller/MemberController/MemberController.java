package de.nordakademie.iaa.hausarbeit.membermgmt.controller.MemberController;


import de.nordakademie.iaa.hausarbeit.membermgmt.model.Member;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.EntityAlreadyPresentException;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.MemberService;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.MembershipStillActiveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping(path="/members")
public class MemberController {


    private MemberService memberService;

    @RequestMapping(method = POST)
    public ResponseEntity<?> createMember(@RequestBody Member member) {
        try {
            memberService.createMember(member);
            return ResponseEntity.status(CREATED).build();
        }
        catch (EntityAlreadyPresentException e) {
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
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
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
        return MemberService.listMembers();
    }

    @Inject
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

}
