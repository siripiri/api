package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.v1.model.lorry.LorryDto;
import com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto;

public interface LorryMapper {
    LorryDto lorryToLorryDto(Lorry lorry);
    LorryDto objectsToLorryWithDriverNameDto(Object[] objects);
    Lorry lorryDtoToLorry(LorryDto lorryDto);
    LorryIdPlateDto objectsToLorryNameWithId(Object[] objects);
    LorryIdPlateDto lorryToLorryIdPlateDto(Lorry lorry);
}
