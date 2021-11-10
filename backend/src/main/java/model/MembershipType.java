package model;

import java.math.BigDecimal;

public class MembershipType {
    private long id;
    private String name;
    private BigDecimal annualFee;

    public MembershipType(long id, String name, BigDecimal annualFee) {
        this.id = id;
        this.name = name;
        this.annualFee = annualFee;
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
