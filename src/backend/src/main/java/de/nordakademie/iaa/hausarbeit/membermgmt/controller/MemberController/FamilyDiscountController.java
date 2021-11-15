package de.nordakademie.iaa.hausarbeit.membermgmt.controller.MemberController;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.FamilyDiscount;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.FamilyDiscountService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(path = "/familydiscount")
public class FamilyDiscountController {

    @RequestMapping(method = GET)
    public FamilyDiscount getFamilyDiscount() {
        return FamilyDiscountService.getFamilyDiscount;
    }
    @RequestMapping(method = PUT)
    public ResponseEntity<?> updateFamilyDiscount(@RequestBody BigDecimal discount) {
        try {
            FamilyDiscountService.updateDiscount(discount);
            return ResponseEntity.status(CREATED).build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(CONFLICT).build();
        }
    }
}
