package com.transport.sabi.api.services;

import com.transport.sabi.api.v1.model.LorryDto;
import com.transport.sabi.api.v1.model.LorryWithDriverNameDto;

import java.util.List;

public interface LorryService {
    List<LorryDto> getAllLorryDto();
    LorryDto getLorryDtoById(Long id);
    List<LorryWithDriverNameDto> getAllLorryWithDriverNameDto();
}
