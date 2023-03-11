package com.transport.sabi.api.v1.model.lorry;

public class LorryIdPlateDto {
    public Long id;
    public String numberPlate;

    public LorryIdPlateDto(Long id, String numberPlate) {
        this.id = id;
        this.numberPlate = numberPlate;
    }

    public LorryIdPlateDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    @Override
    public String toString() {
        return "{" +
                    "id:" + id +
                    ", numberPlate:'" + numberPlate + '\'' +
                '}';
    }
}
