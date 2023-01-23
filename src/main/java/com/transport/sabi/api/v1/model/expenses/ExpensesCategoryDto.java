package com.transport.sabi.api.v1.model.expenses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpensesCategoryDto {
    private Long id;
    private String expenseCategory;
    @JsonProperty("api_url")
    public String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
