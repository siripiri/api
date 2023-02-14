package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.v1.model.lorry.LorryDto;
import com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto;
import org.springframework.stereotype.Component;

@Component
public class LorryMapperImpl implements LorryMapper {
    @Override
    public LorryDto lorryToLorryDto(Lorry lorry) {
        if(lorry == null){
            return null;
        }

        LorryDto lorryDto = new LorryDto();
        lorryDto.setId(lorry.getId());
        lorryDto.setManufacturer(lorry.getManufacturer());
        lorryDto.setNumberPlate(lorry.getNumberPlate());
        lorryDto.setModelNumber(lorry.getModelNumber());
        lorryDto.setType(lorry.getType());

        return lorryDto;
    }

    @Override
    public Lorry lorryDtoToLorry(LorryDto lorryDto) {
        if(lorryDto == null) return null;

        Lorry lorry = new Lorry();
        lorry.setNumberPlate(lorryDto.getNumberPlate());
        lorry.setManufacturer(lorryDto.getManufacturer());
        lorry.setType(lorryDto.getType());
        lorry.setModelNumber(lorryDto.getModelNumber());

        return lorry;
    }

    @Override
    public LorryDto objectsToLorryWithDriverNameDto(Object[] objects) {
        if(objects == null){
            return null;
        }

        LorryDto lorryDto = new LorryDto();
        lorryDto.setId(Long.valueOf(String.valueOf(objects[0])));
        lorryDto.setNumberPlate((String) objects[1]);
        lorryDto.setType((String) objects[2]);
        lorryDto.setModelNumber((String) objects[3]);
        lorryDto.setManufacturer((String) objects[4]);
        lorryDto.setDriverName((String) objects[5]);

        return lorryDto;
    }

    @Override
    public LorryIdPlateDto objectsToLorryNameWithId(Object[] objects) {
        if(objects == null) return null;

        LorryIdPlateDto lorryIdPlateDto = new LorryIdPlateDto();
        lorryIdPlateDto.setId(Long.valueOf(String.valueOf(objects[0])));
        lorryIdPlateDto.setNumberPlate(String.valueOf(objects[1]));
        return lorryIdPlateDto;
    }

    @Override
    public LorryIdPlateDto lorryToLorryIdPlateDto(Lorry lorry) {
       if(lorry == null) return null;

       LorryIdPlateDto lorryIdPlateDto = new LorryIdPlateDto();
       lorryIdPlateDto.setNumberPlate(lorry.getNumberPlate());
       lorryIdPlateDto.setId(lorry.getId());

       return lorryIdPlateDto;
    }
}
