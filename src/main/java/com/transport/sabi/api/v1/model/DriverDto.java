package com.transport.sabi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DriverDto {
    public Long id;
    public Long lorryId;
    public String driverName;
    @JsonProperty("api_url")
    public String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLorryId() {
        return lorryId;
    }

    public void setLorryId(Long lorryId) {
        this.lorryId = lorryId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
