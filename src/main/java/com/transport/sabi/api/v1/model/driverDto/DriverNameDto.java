package com.transport.sabi.api.v1.model.driverDto;

public class DriverNameDto {
    private Long id;
    private String driverName;

    public DriverNameDto(Long id, String driverName) {
        this.id = id;
        this.driverName = driverName;
    }

    public DriverNameDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "{" +
                    "id:" + id +
                    ", driverName:'" + driverName + '\'' +
                '}';
    }
}
