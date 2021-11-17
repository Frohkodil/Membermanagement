package de.nordakademie.iaa.hausarbeit.membermgmt.service;

import de.nordakademie.iaa.hausarbeit.membermgmt.dao.FamilyDiscountDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.FamilyDiscount;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;


@Service
public class FamilyDiscountService {

    private FamilyDiscountDAO familyDiscountDAO;

    public FamilyDiscount loadFamilyDiscount() {

        FamilyDiscount familyDiscount = familyDiscountDAO.get(1L).orElseThrow(EntityNotFoundException::new);
        return familyDiscount;
    }

    public void updateDiscount(BigDecimal discount) {
        FamilyDiscount familyDiscount = loadFamilyDiscount();
        familyDiscount.setDiscount(discount);
    }

    @Inject
    public void setFamilyDiscountDAO(FamilyDiscountDAO familyDiscountDAO) { this.familyDiscountDAO = familyDiscountDAO;}
}
