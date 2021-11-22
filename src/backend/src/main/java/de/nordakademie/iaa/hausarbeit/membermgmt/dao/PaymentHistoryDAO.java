package de.nordakademie.iaa.hausarbeit.membermgmt.dao;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The paymenthistory DAO that manages all persistence functionality.
 *
 * @author Siebo Vogel
 */

@Repository
public class PaymentHistoryDAO{
    /**
     * The current entity manager.
     */
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Returns the PaymentHistory with the given year and paymentstatus
     *
     * @param year Year of the PaymentHistory
     * @param payed PaymentStatus of the PaymentHistory
     * @return a list of PaymentHistory entities. If no PaymentHistory was found an empty list is
     *      * returned.
     */
    public List<PaymentHistory> getPaymentHistoryByYearAndPayment(int year, boolean payed) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentHistory> query = criteriaBuilder.createQuery(PaymentHistory.class);
        Root<PaymentHistory> paymentHistories = query.from(PaymentHistory.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(paymentHistories.get("year"), year));
        predicates.add(criteriaBuilder.equal(paymentHistories.get("payed"), payed));
        query.select(paymentHistories).where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Returns the payment identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */

    public Optional<PaymentHistory> get(Long id) {
        return Optional.ofNullable(entityManager.find(PaymentHistory.class, id));
    }

    /**
     * Returns a List with PaymentHistories
     *
     * @return a list of PaymentHistory entities. If no PaymentHistory was found an empty list is
     *      * returned.
     */
    public List<PaymentHistory> getAll() {
        return entityManager.createQuery("from PaymentHistory", PaymentHistory.class).getResultList();
    }

    /**
     * Takes the Payment and stores it in the database.
     *
     * @param paymentHistory The Payment to be persisted.
     */
    public void save(PaymentHistory paymentHistory) {
        entityManager.persist(paymentHistory);
    }

    public void update(PaymentHistory paymentHistory) {

    }

    public void delete(PaymentHistory paymentHistory) {

    }
}
