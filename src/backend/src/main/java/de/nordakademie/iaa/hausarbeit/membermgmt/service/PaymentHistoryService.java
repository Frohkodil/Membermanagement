package de.nordakademie.iaa.hausarbeit.membermgmt.service;

import de.nordakademie.iaa.hausarbeit.membermgmt.dao.PaymentHistoryDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Siebo Vogel
 */
@Service
public class PaymentHistoryService {
    private PaymentHistoryDAO paymentHistoryDAO;

    /**
     * Updates an existing paymenthistory.
     *
     * @param id The identifier.
     * @param payed The payment status.
     * @param feePayed The fee to be payed.
     * @param dateOfPayment The date when the payment was received.
     * @param year The year of the membershipfee.
     * @throws EntityNotFoundException if the entity can't be found.
     */
    public void updatePaymentHistory(Long id, boolean payed, BigDecimal feePayed, LocalDate dateOfPayment, int year) {
        PaymentHistory paymentHistory = loadPaymentHistory(id);
        if(paymentHistory == null){
            throw new EntityNotFoundException();
        }
        paymentHistory.setPayed(payed);
        paymentHistory.setFeePayed(feePayed);
        paymentHistory.setDateOfPayment(dateOfPayment);
        paymentHistory.setYear(year);
    }

    /**
     * Lists all paymenthistories stored in the database.
     *
     * @return a list of Paymenthistory entities. If no paymenthistory was found an empty list is
     * returned.
     */
    public List<PaymentHistory> listPaymentHistories() {
        return  paymentHistoryDAO.getAll();
    }

    /**
     * Loads a paymenthistory identified by an id.
     *
     * @param id The identifier.
     * @return the found entity.
     * @throws EntityNotFoundException if the entity can't be found.
     */
    public PaymentHistory loadPaymentHistory(Long id) {
        return paymentHistoryDAO.get(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Loads a membershiptype identified by an id.
     *
     * @param year The year.
     * @param payed The payment status.
     * @return a list of Paymenthistory entities. If no paymenthistory was found an empty list is
     * returned.
     */
    public List<PaymentHistory> getPaymentHistoryByYearAndPayment(int year, boolean payed) {
        return paymentHistoryDAO.getPaymentHistoryByYearAndPayment(year, payed);
    }

    /**
     * Takes the new paymenthistory and stores it in the database.
     *
     * @param paymentHistory The membershiptype to be created.
     */
    public void createPaymentHistory(PaymentHistory paymentHistory){
            paymentHistoryDAO.save(paymentHistory);

    }

    @Inject
    public void setPaymentHistoryDAO(PaymentHistoryDAO paymentHistoryDAO) {
        this.paymentHistoryDAO = paymentHistoryDAO;
    }
}
