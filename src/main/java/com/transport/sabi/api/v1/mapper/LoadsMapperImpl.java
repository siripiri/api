package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.load.CylinderDetail;
import com.transport.sabi.api.v1.model.loads.CylinderDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class LoadsMapperImpl implements LoadsMapper {
    @Override
    public CylinderDetailsDto cylinderDetailsToCylinderDetailsDto(CylinderDetail cylinderDetail) {
        if(cylinderDetail == null) return null;

        CylinderDetailsDto cylinderDetailsDto = new CylinderDetailsDto();
        cylinderDetailsDto.setType(cylinderDetail.getType());
        cylinderDetailsDto.setDescription(cylinderDetail.getDescription());
        cylinderDetailsDto.setId(cylinderDetail.getId());
        cylinderDetailsDto.setUrl("/api/v1/cylinder/" + cylinderDetail.getId());

        return cylinderDetailsDto;
    }
}
