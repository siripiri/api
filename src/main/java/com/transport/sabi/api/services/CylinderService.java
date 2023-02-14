package com.transport.sabi.api.services;

import com.transport.sabi.api.v1.model.loads.CylinderDetailsDto;

import java.util.List;

public interface CylinderService {
    List<CylinderDetailsDto> getAllCylinder();
}
