package de.nordakademie.iaa.hausarbeit.membermgmt.service;

import de.nordakademie.iaa.hausarbeit.membermgmt.dao.PaymentHistoryDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PaymentHistoryService {
    private PaymentHistoryDAO paymentHistoryDAO;

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

    public List<PaymentHistory> listPaymentHistories() {
        return paymentHistoryDAO.listPaymentHistory();
    }

    public PaymentHistory loadPaymentHistory(Long id) {
        PaymentHistory paymentHistory = paymentHistoryDAO.loadPaymentHistory(id);
        if(paymentHistory == null) {
            throw new EntityNotFoundException();
        }
        return paymentHistory;
    }

    public List<PaymentHistory> getPaymentHistoryByYear(int year) {
        return paymentHistoryDAO.listPaymentHistoryByYear(year);
    }

    public void createPaymentHistory(PaymentHistory paymentHistory) throws EntityAlreadyPresentException {
        try {
            paymentHistoryDAO.persistPaymentHistory(paymentHistory);
        } catch (ConstraintViolationException e) {
            throw new EntityAlreadyPresentException();
        }
    }

    @Inject
    public void setPaymentHistoryDAO(PaymentHistoryDAO paymentHistoryDAO) {
        this.paymentHistoryDAO = paymentHistoryDAO;
    }
}
