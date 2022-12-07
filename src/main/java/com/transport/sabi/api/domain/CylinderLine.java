package com.transport.sabi.api.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CylinderLine extends BaseEntity {
    @ManyToOne
    public TripDetail tripDetail;
    @OneToOne
    public CylinderDetail cylinderDetail;
    private Long quantity;

    public TripDetail getTripDetail() {
        return tripDetail;
    }

    public void setTripDetail(TripDetail tripDetail) {
        this.tripDetail = tripDetail;
    }

    public CylinderDetail getCylinderDetail() {
        return cylinderDetail;
    }

    public void setCylinderDetail(CylinderDetail cylinderDetail) {
        this.cylinderDetail = cylinderDetail;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
