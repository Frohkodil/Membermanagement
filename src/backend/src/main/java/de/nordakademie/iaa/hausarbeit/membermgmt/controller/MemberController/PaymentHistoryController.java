package de.nordakademie.iaa.hausarbeit.membermgmt.controller.MemberController;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.EntityAlreadyPresentException;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.PaymentHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/paymenthistories")
public class PaymentHistoryController {
    private PaymentHistoryService paymentHistoryService;

    @RequestMapping(method = GET)
    public List<PaymentHistory> listPaymentHistories() {
        return paymentHistoryService.listPaymentHistories();
    }

    @RequestMapping(path = "/id/{id}", method = GET)
    public ResponseEntity<?> loadPaymentHistory(@PathVariable("id") Long id) {
        try {
            paymentHistoryService.loadPaymentHistory(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(path = "/year/{year}", method = GET)
    public List<PaymentHistory> listPaymentHistoriesByYear(@PathVariable("year") int year) {
        return paymentHistoryService.getPaymentHistoryByYear(year);
    }

    @RequestMapping(path = "/id/{id}",method = PUT)
    public ResponseEntity<?> updatePaymentHistory(@PathVariable("id") Long id, @RequestBody PaymentHistory paymentHistory) {
        try {
            paymentHistoryService.updatePaymentHistory(id, paymentHistory.isPayed(), paymentHistory.getFeePayed(), paymentHistory.getDateOfPayment(), paymentHistory.getYear());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> createPaymentHistory(@RequestBody PaymentHistory paymentHistory) {
        try {
            paymentHistoryService.createPaymentHistory(paymentHistory);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityAlreadyPresentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
