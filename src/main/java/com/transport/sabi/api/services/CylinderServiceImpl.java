package com.transport.sabi.api.services;

import com.transport.sabi.api.repository.CylinderDetailRepository;
import com.transport.sabi.api.v1.mapper.LoadsMapper;
import com.transport.sabi.api.v1.model.loads.CylinderDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CylinderServiceImpl implements CylinderService {

    private final CylinderDetailRepository cylinderDetailRepository;
    private final LoadsMapper loadsMapper;

    public CylinderServiceImpl(CylinderDetailRepository cylinderDetailRepository, LoadsMapper loadsMapper) {
        this.cylinderDetailRepository = cylinderDetailRepository;
        this.loadsMapper = loadsMapper;
    }

    @Override
    public List<CylinderDetailsDto> getAllCylinder() {
        return cylinderDetailRepository.findAll()
                .stream()
                .map(loadsMapper::cylinderDetailsToCylinderDetailsDto)
                .collect(Collectors.toList());
    }
}
