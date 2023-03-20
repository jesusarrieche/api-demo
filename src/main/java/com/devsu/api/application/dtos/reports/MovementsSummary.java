package com.devsu.api.application.dtos.reports;

import java.math.BigDecimal;

public class MovementsSummary {

    private BigDecimal totalAmount;
    private Long totalMovements;

    public MovementsSummary(BigDecimal totalAmount, Long totalMovements) {
        this.totalAmount = totalAmount;
        this.totalMovements = totalMovements;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalMovements() {
        return totalMovements;
    }

    public void setTotalMovements(Long totalMovements) {
        this.totalMovements = totalMovements;
    }
}
