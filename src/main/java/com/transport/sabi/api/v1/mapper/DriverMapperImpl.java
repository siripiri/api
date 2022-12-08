package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.v1.model.DriverDto;
import org.springframework.stereotype.Component;

@Component
public class DriverMapperImpl implements DriverMapper {
    @Override
    public DriverDto driverNamesWithLorryId(Object[] objects) {

        if(objects == null) {
            System.out.println("Null");
            return null;
        }

        DriverDto driverDto =  new DriverDto();
        if(objects[0] != null) {
            driverDto.setLorryId(Long.valueOf(String.valueOf(objects[0])));
        }
        driverDto.setId(Long.valueOf(String.valueOf(objects[1])));
        driverDto.setDriverName(String.valueOf(objects[2]));
        return driverDto;
    }
}
