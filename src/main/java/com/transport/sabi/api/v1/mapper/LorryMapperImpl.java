package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.v1.model.LorryDto;
import com.transport.sabi.api.v1.model.LorryWithDriverNameDto;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

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
    public LorryWithDriverNameDto lorryToLorryWithDriverNameDto(Object[] objects) {
        if(objects == null){
            return null;
        }

        LorryWithDriverNameDto lorryWithDriverNameDto = new LorryWithDriverNameDto();
        lorryWithDriverNameDto.setId(Long.valueOf(String.valueOf(objects[0])));
        lorryWithDriverNameDto.setNumberPlate((String) objects[1]);
        lorryWithDriverNameDto.setType((String) objects[2]);
        lorryWithDriverNameDto.setModelNumber((String) objects[3]);
        lorryWithDriverNameDto.setManufacturer((String) objects[4]);
        lorryWithDriverNameDto.setDriverName((String) objects[5]);

        return lorryWithDriverNameDto;
    }
}
