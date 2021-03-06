package de.nordakademie.iaa.hausarbeit.membermgmt.controller;


import de.nordakademie.iaa.hausarbeit.membermgmt.model.Membership;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.MembershipType;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.EntityAlreadyPresentException;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.IllegalDataException;
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
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * REST controller for the MembershipType entity.
 *
 * @author Siebo Vogel
 */

@RestController
@RequestMapping(path = "/MembershipTypes")
public class MembershipTypeController {
    private MembershipTypeService membershipTypeService;

    /**
     * Creates a MembershipType.
     *
     * @param membershipType MembershipType to be created
     * @return a response entity.
     */
    @RequestMapping(method = POST)
    public ResponseEntity<?> createMembershipType(@Valid @RequestBody MembershipType membershipType) {
        membershipTypeService.createMembershipType(membershipType);
        return ResponseEntity.status(CREATED).build();
    }

    /**
     * Updates a MembershipType.
     *
     * @param id ID of the MembershipType to be updated
     * @param membershipType updated MembershipType
     * @return a response entity.
     */
    @RequestMapping(path = "/{id}", method = PUT)
    public ResponseEntity<?> updateMembership(@PathVariable("id") Long id,  @Valid @RequestBody MembershipType membershipType) {
        try{
            membershipTypeService.updateMembershipType(id, membershipType.getName(), membershipType.getAnnualFee());
            return ResponseEntity.status(CREATED).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(CONFLICT).build();
        }
    }

    /**
     * Lists all MembershipTypes.
     *
     * @return List of MembershipTypes.
     */
    @RequestMapping(method = GET)
    public List<MembershipType> listMembershipTypes() {
        return membershipTypeService.listMembershipTypes();
    }

    /**
     * Creates a Membership.
     *
     * @param id ID of the room the be found.
     * @return a response entity.
     */
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
