package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class MembershipDAO {
    private EntityManager entityManager;


    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
