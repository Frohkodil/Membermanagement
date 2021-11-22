package de.nordakademie.iaa.hausarbeit.membermgmt.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * PaymentHistory entity.
 *
 * @author Siebo Vogel
 */

@Entity
public class PaymentHistory implements Serializable {

    private static final long serialVersionUID = -1133371029165937387L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean payed;
    private BigDecimal feePayed;
    @Past
    private LocalDate dateOfPayment;
    @Min(value = 1990)
    private int year;
    @NotNull(message = "Zu einer Zahlung sollte ein Mitglied gehören.")
    @JoinColumn
    @ManyToOne
    @Valid
    private Member member;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
