package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.FamilyDiscount;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PaymentHistoryDAO {
    private EntityManager entityManager;

    public void persistPaymentHistory(PaymentHistory paymentHistory) {
        entityManager.persist(paymentHistory);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
