package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.v1.model.LorryDto;

public interface LorryMapper {
    LorryDto lorryToLorryDto(Lorry lorry);
    LorryDto objectsToLorryWithDriverNameDto(Object[] objects);
    Lorry lorryDtoToLorry(LorryDto lorryDto);
}
