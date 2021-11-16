package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PaymentHistoryDAO {
    private EntityManager entityManager;

    public void persistPaymentHistory(PaymentHistory paymentHistory) {
        entityManager.persist(paymentHistory);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public PaymentHistory loadPaymentHistory(Long id) {
        return entityManager.find(PaymentHistory.class, id);
    }

    public List<PaymentHistory> listPaymentHistoryByYear(int year) {
        return entityManager.createQuery("select * from PaymentHistory where year = year").getResultList();
    }

    public List<PaymentHistory> listPaymentHistory() {
        return entityManager.createQuery("select * from PaymentHistory").getResultList();
    }
}
