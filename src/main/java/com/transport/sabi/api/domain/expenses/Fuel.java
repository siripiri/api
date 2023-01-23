package com.transport.sabi.api.domain.expenses;

import com.transport.sabi.api.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Fuel extends BaseEntity {
    private String currentPrice;
    private Long literFilled;
    private String paymentMode;
    @OneToOne
    private Expenses expenses;

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getLiterFilled() {
        return literFilled;
    }

    public void setLiterFilled(Long literFilled) {
        this.literFilled = literFilled;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(Expenses expenses) {
        this.expenses = expenses;
    }
}
