package de.nordakademie.iaa.hausarbeit.membermgmt.controller;

import de.nordakademie.iaa.hausarbeit.membermgmt.model.PaymentHistory;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.EntityAlreadyPresentException;
import de.nordakademie.iaa.hausarbeit.membermgmt.service.PaymentHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * REST controller for the PaymentHistory entity.
 *
 * @author Siebo Vogel
 */


@RestController
@RequestMapping(path = "/paymenthistories")
public class PaymentHistoryController {
    private PaymentHistoryService paymentHistoryService;

    /**
     * Lists all PaymentHistories.
     *
     * @return a response entity with a List of PaymentHistories.
     */
    @RequestMapping(method = GET)
    public ResponseEntity<List<PaymentHistory>> listPaymentHistories() {
        return ResponseEntity.ok(paymentHistoryService.listPaymentHistories());
    }

    /**
     * Gets a PaymentHistory by its ID.
     *
     * @param id ID of the PaymentHistory
     * @return a response entity.
     */
    @RequestMapping(path = "/id/{id}", method = GET)
    public ResponseEntity<PaymentHistory> loadPaymentHistory(@PathVariable("id") Long id) {
        try {
            paymentHistoryService.loadPaymentHistory(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Lists PaymentHistorys by year and its payment status.
     *
     * @param year Year of the paymenthistories
     * @param payed Wether the amount was payed
     * @return a response entity with a list of the PaymentHistories.
     */
    @RequestMapping(path = "/year/{year}/payed/{payed}", method = GET)
    public ResponseEntity<?> listPaymentHistoriesByYear(@PathVariable int year, @PathVariable boolean payed) {
        return ResponseEntity.ok(paymentHistoryService.getPaymentHistoryByYearAndPayment(year, payed));
    }

    /**
     * Updates a PaymentHistory by its ID.
     *
     * @param id ID of the PaymentHistory.
     * @return a response entity.
     */
    @RequestMapping(path = "/id/{id}",method = PUT)
    public ResponseEntity<?> updatePaymentHistory(@PathVariable("id") Long id, @Valid @RequestBody PaymentHistory paymentHistory) {
        try {
            paymentHistoryService.updatePaymentHistory(id, paymentHistory.isPayed(), paymentHistory.getFeePayed(), paymentHistory.getDateOfPayment(), paymentHistory.getYear());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Creates a PaymentHistory.
     *
     * @param paymentHistory PaymentHistory to be created.
     * @return a response entity.
     */
    @RequestMapping(method = POST)
    public ResponseEntity<?> createPaymentHistory(@Valid @RequestBody PaymentHistory paymentHistory) {
        paymentHistoryService.createPaymentHistory(paymentHistory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Inject
    public void setPaymentHistoryService(PaymentHistoryService paymentHistoryService) {
        this.paymentHistoryService = paymentHistoryService;
    }

}
