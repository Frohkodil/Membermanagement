package de.nordakademie.iaa.hausarbeit.membermgmt.service;

import de.nordakademie.iaa.hausarbeit.membermgmt.dao.FamilyDiscountDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.FamilyDiscount;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;


/**
 * The service for the familydiscount.
 *
 * @author Siebo Vogel
 */
@Service
@Transactional
public class FamilyDiscountService {

    private FamilyDiscountDAO familyDiscountDAO;

    /**
     * Returns the familyDiscount.
     *
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public FamilyDiscount loadFamilyDiscount() {
        FamilyDiscount familyDiscount = familyDiscountDAO.get().orElseThrow(EntityNotFoundException::new);
        return familyDiscount;
    }

    /**
     * Updates the familydiscount
     *
     * @param discount the new amount of the discount.
     */
    public void updateDiscount(BigDecimal discount) {
        FamilyDiscount familyDiscount = loadFamilyDiscount();
        familyDiscount.setDiscount(discount);
    }

    @Inject
    public void setFamilyDiscountDAO(FamilyDiscountDAO familyDiscountDAO) { this.familyDiscountDAO = familyDiscountDAO;}
}
