package de.nordakademie.iaa.hausarbeit.membermgmt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class MembershipType implements Serializable {

    private static final long serialVersionUID = -2762494933783891656L;

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private BigDecimal annualFee;

    public MembershipType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(BigDecimal annualFee) {
        this.annualFee = annualFee;
    }
}
