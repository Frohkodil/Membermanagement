package de.nordakademie.iaa.hausarbeit.membermgmt.controller;


import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.EntityAlreadyPresentException;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.MembershipTypeService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/MembershipTypes")
public class MembershipTypeController {
    private MembershipTypeService membershipTypeService;


    @RequestMapping(method = POST)
    public ResponseEntity<?> createMembership(@RequestBody MembershipType membershipType) {
        try {
            membershipTypeService.createMembershipType(membershipType);
            return ResponseEntity.status(CREATED).build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityAlreadyPresentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RequestMapping(path = "/{id}", method = PUT)
    public ResponseEntity<?> updateMembership(@RequestBody Long id, String name, BigDecimal annualFee) {
        try{
            membershipTypeService.updateMembershipType(id, name, annualFee);
            return ResponseEntity.status(CREATED).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(CONFLICT).build();

        }
    }

    @RequestMapping(method = GET)
    public List<MembershipType> listMembershipTypes() {
        return membershipTypeService.listMembershipTypes();
    }

    @RequestMapping(path="/{id}", method = GET)
    public ResponseEntity<?> loadMembership(@PathVariable("id") Long id) {
        try {
            membershipTypeService.loadMembershipType(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Inject
    public void setMembershipService(MembershipTypeService membershipTypeService) {
        this.membershipTypeService = membershipTypeService;
    }
}
