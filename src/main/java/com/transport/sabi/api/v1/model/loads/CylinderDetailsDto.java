package com.transport.sabi.api.v1.model.loads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CylinderDetailsDto {
    private Long id;
    private String type;
    private String description;
    @JsonProperty("api_url")
    public String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
