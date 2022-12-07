package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.v1.model.DriverNamesWithLorryDto;
import org.springframework.stereotype.Component;

@Component
public class DriverMapperImpl implements DriverMapper {
    @Override
    public DriverNamesWithLorryDto driverNamesWithLorryId(Object[] objects) {

        if(objects == null) {
            System.out.println("Null");
            return null;
        }

        DriverNamesWithLorryDto driverNamesWithLorryDto =  new DriverNamesWithLorryDto();
        if(objects[0] != null) {
            driverNamesWithLorryDto.setLorryId(Long.valueOf(String.valueOf(objects[0])));
        }
        driverNamesWithLorryDto.setDriverId(Long.valueOf(String.valueOf(objects[1])));
        driverNamesWithLorryDto.setDriverName(String.valueOf(objects[2]));
        return driverNamesWithLorryDto;
    }
}
