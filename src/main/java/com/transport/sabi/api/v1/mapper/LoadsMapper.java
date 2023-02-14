package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.load.CylinderDetail;
import com.transport.sabi.api.v1.model.loads.CylinderDetailsDto;

public interface LoadsMapper {
    CylinderDetailsDto cylinderDetailsToCylinderDetailsDto(CylinderDetail cylinderDetail);
}
