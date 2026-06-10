package com.campusexpress.dto;


public class OutboundRequest {
    private String trackingNumber;
    private String pickupCode;

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getPickupCode() {
        return this.pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode;
    }
}
