package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.FamilyDiscount;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class FamilyDiscountDAO {
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<FamilyDiscount> get(Long id) {
        FamilyDiscount familydiscount = entityManager.find(FamilyDiscount.class, 1L);
        return Optional.ofNullable(familydiscount);
    }

    public void save(FamilyDiscount familyDiscount) {
        entityManager.persist(familyDiscount);
    }


}
