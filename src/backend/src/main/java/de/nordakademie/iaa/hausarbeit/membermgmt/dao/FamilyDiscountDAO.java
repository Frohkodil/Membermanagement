package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.FamilyDiscount;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FamilyDiscountDAO {
    private EntityManager entityManager;

    public List loadFamilyDiscount() {
        return entityManager.createQuery("select familydiscount from FamilyDiscount familydiscount").getResultList();
    }

    public void persistFamilyDiscount(FamilyDiscount familyDiscount) {
        entityManager.persist(familyDiscount);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
