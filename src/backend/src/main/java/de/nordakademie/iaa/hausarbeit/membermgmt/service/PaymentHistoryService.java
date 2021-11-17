package de.nordakademie.iaa.hausarbeit.membermgmt.service;

import de.nordakademie.iaa.hausarbeit.membermgmt.dao.PaymentHistoryDAO;
import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PaymentHistoryService {
    private PaymentHistoryDAO paymentHistoryDAO;

    public void updatePaymentHistory(Long id, boolean payed, BigDecimal feePayed, Date dateOfPayment, int year) {
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
        return paymentHistoryDAO.getAll();
    }

    public PaymentHistory loadPaymentHistory(Long id) {
        return paymentHistoryDAO.get(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<PaymentHistory> getPaymentHistoryByYear(int year) {
        return paymentHistoryDAO.getPaymentHistoryByYear(year);
    }

    public void createPaymentHistory(PaymentHistory paymentHistory) throws EntityAlreadyPresentException {
        try {
            paymentHistoryDAO.save(paymentHistory);
        } catch (ConstraintViolationException e) {
            throw new EntityAlreadyPresentException();
        }
    }

    @Inject
    public void setPaymentHistoryDAO(PaymentHistoryDAO paymentHistoryDAO) {
        this.paymentHistoryDAO = paymentHistoryDAO;
    }
}
