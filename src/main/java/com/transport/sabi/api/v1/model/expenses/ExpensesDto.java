package com.transport.sabi.api.v1.model.expenses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transport.sabi.api.v1.model.driverDto.DriverNameDto;
import com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto;

public class ExpensesDto {

    private Long id;
    private String name;
    private String amount;
    private String notes;
    private String date;
    private String expensesCategory;
    private LorryIdPlateDto lorry;
    private DriverNameDto driver;
    @JsonProperty("api_url")
    public String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpensesCategory() {
        return expensesCategory;
    }

    public void setExpensesCategory(String expensesCategory) {
        this.expensesCategory = expensesCategory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LorryIdPlateDto getLorry() {
        return lorry;
    }

    public void setLorry(LorryIdPlateDto lorry) {
        this.lorry = lorry;
    }

    public DriverNameDto getDriver() {
        return driver;
    }

    public void setDriver(DriverNameDto driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "{" +
                    "id:" + id +
                    ", name:'" + name + '\'' +
                    ", amount:'" + amount + '\'' +
                    ", notes:'" + notes + '\'' +
                    ", date:'" + date + '\'' +
                    ", expensesCategory:'" + expensesCategory + '\'' +
                    ", lorry:" + (lorry!=null ? this.getLorry().toString() : "null") +
                    ", driver:" + (driver!=null ? this.getDriver().toString() : "null") +
                    ", url:'" + url + '\'' +
                '}';
    }
}
