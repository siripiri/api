package com.transport.sabi.api.services;

import com.transport.sabi.api.dao.QueryDao;
import com.transport.sabi.api.domain.Driver;
import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.domain.repository.DriverRepository;
import com.transport.sabi.api.domain.repository.LorryRepository;
import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import com.transport.sabi.api.v1.mapper.LorryMapper;
import com.transport.sabi.api.v1.model.LorryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LorryServiceImpl implements LorryService {

    private final LorryRepository lorryRepository;
    private final DriverRepository driverRepository;
    private final QueryDao queryDao;
    private final LorryMapper lorryMapper;

    public LorryServiceImpl(LorryRepository lorryRepository, LorryMapper lorryMapper, DriverRepository driverRepository, QueryDao queryDao) {
        this.lorryRepository = lorryRepository;
        this.lorryMapper = lorryMapper;
        this.driverRepository = driverRepository;
        this.queryDao = queryDao;
    }

    @Override
    public List<LorryDto> getAllLorryDto() {
        return lorryRepository.findAll()
                .stream()
                .map(lorryMapper::lorryToLorryDto)
                .map(lorryDto -> {
                    lorryDto.setUrl("/api/v1/lorry/" + lorryDto.getId());
                    return lorryDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public LorryDto getLorryDtoById(Long id) {
        return lorryRepository.findById(id)
                .map(lorryMapper::lorryToLorryDto)
                .map(lorryDto -> {
                    lorryDto.setUrl("/api/v1/lorry/" + lorryDto.getId());
                    return lorryDto;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<LorryDto> getAllLorryWithDriverNameDto() {
        return queryDao.getAllLorryAndDriverName()
                .stream()
                .map(lorryMapper::objectsToLorryWithDriverNameDto)
                .map(lorryWithDriverNameDto -> {
                    lorryWithDriverNameDto.setUrl("/api/v1/lorry/driverName/" + lorryWithDriverNameDto.getId());
                    return lorryWithDriverNameDto;
                }).collect(Collectors.toList());
    }

    @Override
    public LorryDto saveLorry(LorryDto lorryDto) {
        Lorry lorry = lorryMapper.lorryDtoToLorry(lorryDto);
        if(lorryDto.getDriverId() != null) {
            Driver driver = driverRepository.getReferenceById(lorryDto.getDriverId());
            lorry.setDrivers(Set.of(driver));
        }

        Lorry savedLorry = lorryRepository.saveAndFlush(lorry);

        LorryDto savedLorryDto = lorryMapper.lorryToLorryDto(savedLorry);
        savedLorryDto.setUrl("/api/v1/lorry/" + savedLorryDto.getId());

        return savedLorryDto;
    }
}
