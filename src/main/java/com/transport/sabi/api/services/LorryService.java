package com.transport.sabi.api.services;

import com.transport.sabi.api.v1.model.LorryDto;

import java.util.List;

public interface LorryService {
    List<LorryDto> getAllLorryDto();
    LorryDto getLorryDtoById(Long id);
    List<LorryDto> getAllLorryWithDriverNameDto();
    LorryDto saveLorry(LorryDto lorryDto);
    LorryDto updateLorry(LorryDto lorryDto);
    LorryDto unassignDriver(LorryDto lorryDto);
    LorryDto assignDriver(LorryDto lorryDto);
}
