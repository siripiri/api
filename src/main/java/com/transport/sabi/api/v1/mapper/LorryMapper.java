package com.transport.sabi.api.v1.mapper;

import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.v1.model.LorryDto;
import com.transport.sabi.api.v1.model.LorryWithDriverNameDto;

public interface LorryMapper {
    public LorryDto lorryToLorryDto(Lorry lorry);
    public LorryWithDriverNameDto lorryToLorryWithDriverNameDto(Object[] objects);
}
