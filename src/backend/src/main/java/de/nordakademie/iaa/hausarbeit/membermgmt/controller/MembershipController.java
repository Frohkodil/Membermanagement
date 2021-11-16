package de.nordakademie.iaa.hausarbeit.membermgmt.controller;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.EntityAlreadyPresentException;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.MembershipService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/memberships")
public class MembershipController {
        private MembershipService membershipService;


        @RequestMapping(method = POST)
        public ResponseEntity<?> createMembership(@RequestBody Membership membership) {
            try {
                membershipService.createMembership(membership);
                return ResponseEntity.status(CREATED).build();
            } catch (ConstraintViolationException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } catch (EntityAlreadyPresentException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }

        @RequestMapping(path = "/{id}", method = PUT)
        public ResponseEntity<?> updateMembership(@RequestBody Long id, Date startDate, Date endDate, Date cancellationDate, MembershipType membershipType) {
            try{
                membershipService.updateMembership(id, startDate, endDate, cancellationDate, membershipType);
                return ResponseEntity.status(CREATED).build();
            } catch (EntityNotFoundException e) {
                return ResponseEntity.status(CONFLICT).build();

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
