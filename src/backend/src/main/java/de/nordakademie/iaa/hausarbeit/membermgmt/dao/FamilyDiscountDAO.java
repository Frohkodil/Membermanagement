package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.FamilyDiscount;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * The familydiscount DAO that manages all persistence functionality.
 *
 * @author Siebo Vogel
 */

@Repository
public class FamilyDiscountDAO {
    /**
     * The current entity manager.
     */
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Returns the familydiscount identified by the given id.
     *
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    public Optional<FamilyDiscount> get() {
        FamilyDiscount familydiscount = entityManager.find(FamilyDiscount.class, 1L);
        return Optional.ofNullable(familydiscount);
    }
}
