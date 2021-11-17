package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentHistoryDAO implements DAO<PaymentHistory>{
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<PaymentHistory> getPaymentHistoryByYear(int year) {
        return entityManager.createQuery("select * from PaymentHistory where year = year").getResultList();
    }

    @Override
    public Optional<PaymentHistory> get(Long id) {
        return Optional.ofNullable(entityManager.find(PaymentHistory.class, id));
    }

    @Override
    public List<PaymentHistory> getAll() {
        return entityManager.createQuery("select * from PaymentHistory").getResultList();
    }

    @Override
    public void save(PaymentHistory paymentHistory) {
        entityManager.persist(paymentHistory);
    }

    @Override
    public void update(PaymentHistory paymentHistory) {

    }

    @Override
    public void delete(PaymentHistory paymentHistory) {

    }
}
