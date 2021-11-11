package de.nordakademie.iaa.hausarbeit.membermgmt.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class PaymentHistory implements Serializable {

    private static final long serialVersionUID = -1133371029165937387L;

    @Id
    @GeneratedValue
    private Long id;
    private boolean payed;
    private BigDecimal feePayed;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfPayment;
    private int year;

    public PaymentHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public BigDecimal getFeePayed() {
        return feePayed;
    }

    public void setFeePayed(BigDecimal feePayed) {
        this.feePayed = feePayed;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
