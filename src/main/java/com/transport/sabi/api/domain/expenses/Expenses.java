package com.transport.sabi.api.domain.expenses;

import com.transport.sabi.api.domain.BaseEntity;
import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.domain.driver.Driver;

import jakarta.persistence.*;

@Entity
public class Expenses extends BaseEntity {
    private String name;
    private String amount;
    private String date;
    private String notes;
    @ManyToOne
    private ExpensesCategory expensesCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;
    @ManyToOne(fetch = FetchType.LAZY)
    private Lorry lorry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ExpensesCategory getExpensesCategory() {
        return expensesCategory;
    }

    public void setExpensesCategory(ExpensesCategory expensesCategory) {
        this.expensesCategory = expensesCategory;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Lorry getLorry() {
        return lorry;
    }

    public void setLorry(Lorry lorry) {
        this.lorry = lorry;
    }
}
