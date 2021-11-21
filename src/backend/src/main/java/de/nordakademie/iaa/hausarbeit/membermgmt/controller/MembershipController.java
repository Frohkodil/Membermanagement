package de.nordakademie.iaa.hausarbeit.membermgmt.controller;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.EntityAlreadyPresentException;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.IllegalDateException;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.MembershipService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/memberships")
public class MembershipController {

        private MembershipService membershipService;


        @RequestMapping(method = POST)
        public ResponseEntity<?> createMembership(@Valid @RequestBody Membership membership) {
            try {
                membershipService.createMembership(membership);
                return ResponseEntity.status(CREATED).build();
            } catch (IllegalDateException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }

        @RequestMapping(path = "/{id}", method = PUT)
        public ResponseEntity<?> updateMembership(@PathVariable("id") Long id,   @Valid @RequestBody Membership membership) {
            try{
                membershipService.updateMembership(id, membership.getStartDate(), membership.getEndDate(), membership.getCancellationDate(), membership.getMembershipType());
                return ResponseEntity.status(CREATED).build();
            } catch (EntityNotFoundException e) {
                return ResponseEntity.status(CONFLICT).build();
            } catch (IllegalDateException e) {
                return ResponseEntity.status(BAD_REQUEST).body("Änderungen der Mitgliedschaft sind nur zum Anfang des Jahres möglich.");
            }
        }

        @RequestMapping(method = GET)
        public List<Membership> listMemberships() {
            return membershipService.listMemberships();
        }

        @RequestMapping(path="/{id}", method = GET)
        public ResponseEntity<?> loadMembership(@PathVariable("id") Long id) {
            try {
                membershipService.loadMembership(id);
                return ResponseEntity.ok().build();
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }

        @Inject
        public void setMembershipService(MembershipService membershipService) {
            this.membershipService = membershipService;
        }
}
