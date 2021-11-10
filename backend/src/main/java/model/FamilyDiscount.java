package model;

import java.math.BigDecimal;

public class FamilyDiscount {
    private BigDecimal discount;

    public FamilyDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
