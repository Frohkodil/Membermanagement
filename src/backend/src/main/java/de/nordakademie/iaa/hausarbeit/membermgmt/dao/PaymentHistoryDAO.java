package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentHistoryDAO{
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<PaymentHistory> getPaymentHistoryByYear(int year) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentHistory> query = criteriaBuilder.createQuery(PaymentHistory.class);
        Root<PaymentHistory> paymentHistories = query.from(PaymentHistory.class);
        query.select(paymentHistories).where(criteriaBuilder.equal(paymentHistories.get("year"), year));
        return entityManager.createQuery(query).getResultList();
    }

    public Optional<PaymentHistory> get(Long id) {
        return Optional.ofNullable(entityManager.find(PaymentHistory.class, id));
    }

    public List<PaymentHistory> getAll() {
        return entityManager.createQuery("from PaymentHistory", PaymentHistory.class).getResultList();
    }

    public void save(PaymentHistory paymentHistory) {
        entityManager.persist(paymentHistory);
    }

    public void update(PaymentHistory paymentHistory) {

    }

    public void delete(PaymentHistory paymentHistory) {

    }
}
