package com.transport.sabi.api.domain.load;

import com.transport.sabi.api.domain.BaseEntity;

import jakarta.persistence.Entity;

@Entity
public class CylinderDetail extends BaseEntity {
    private String type;
    private String description;

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
}
