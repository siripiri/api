package com.transport.sabi.api.v1.model.expenses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FuelDto {
    private Long id;
    private String currentPrice;
    private Long literFilled;
    private String paymentMode;
    private ExpensesDto expenses;
    @JsonProperty("api_url")
    public String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ExpensesDto getExpenses() {
        return expenses;
    }

    public void setExpenses(ExpensesDto expenses) {
        this.expenses = expenses;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
