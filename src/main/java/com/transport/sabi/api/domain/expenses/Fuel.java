package com.transport.sabi.api.domain.expenses;

import com.transport.sabi.api.domain.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Fuel extends BaseEntity {
    private String currentPrice;
    private String literFilled;
    private String paymentMode;
    @OneToOne
    private Expenses expenses;

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getLiterFilled() {
        return literFilled;
    }

    public void setLiterFilled(String literFilled) {
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
