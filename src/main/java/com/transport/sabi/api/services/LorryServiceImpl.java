package com.transport.sabi.api.services;

import com.transport.sabi.api.dao.HibernateDao;
import com.transport.sabi.api.dao.QueryDao;
import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.domain.repository.DriverRepository;
import com.transport.sabi.api.domain.repository.LorryRepository;
import com.transport.sabi.api.services.exception.BadRequestException;
import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import com.transport.sabi.api.v1.mapper.LorryMapper;
import com.transport.sabi.api.v1.model.LorryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LorryServiceImpl implements LorryService {

    private final LorryRepository lorryRepository;
    private final DriverRepository driverRepository;
    private final QueryDao queryDao;
    private final LorryMapper lorryMapper;
    private final HibernateDao hibernateDao;

    public LorryServiceImpl(LorryRepository lorryRepository, LorryMapper lorryMapper, DriverRepository driverRepository, QueryDao queryDao, HibernateDao hibernateDao) {
        this.lorryRepository = lorryRepository;
        this.lorryMapper = lorryMapper;
        this.driverRepository = driverRepository;
        this.queryDao = queryDao;
        this.hibernateDao = hibernateDao;
    }

    @Override
    public List<LorryDto> getAllLorryDto() {
        return lorryRepository.findAll()
                .stream()
                .map(lorryMapper::lorryToLorryDto)
                .peek(lorryDto -> lorryDto.setUrl("/api/v1/lorry/" + lorryDto.getId()))
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

    private boolean numberPlateCheck(String numberPlate) {
        Lorry lorry = this.lorryRepository.findByNumberPlate(numberPlate).orElse(null);
        return lorry == null;
    }

    @Override
    public LorryDto assignDriver(LorryDto lorryDto) {
        Driver driver = driverRepository.findByName(lorryDto.getDriverName()).orElse(null);
        if(driver == null) {
            throw new BadRequestException("driver is null");
        }
        this.queryDao.insertAssignDriver(driver.getId(), lorryDto.getId());
        Lorry updatedLorry = this.lorryRepository.getReferenceById(lorryDto.getId());
        LorryDto savedLorryDto = this.lorryMapper.lorryToLorryDto(updatedLorry);
        savedLorryDto.setDriverName(driver.getName());
        savedLorryDto.setUrl("/api/v1/lorry/" + savedLorryDto.getId());
        return savedLorryDto;
    }

    @Override
    public LorryDto unassignDriver(LorryDto lorryDto) {
        System.out.println("Hello "+lorryDto.getId());
        Lorry lorry = lorryRepository.findById(lorryDto.getId()).orElse(null);
        Driver driver = null;
        if(lorryDto.driverId != null) {
            driver = driverRepository.findById(lorryDto.getDriverId()).orElse(null);
        } else {
            driver = driverRepository.findByName(lorryDto.getDriverName()).orElse(null);
        }

        if(lorry == null || driver == null) {
            throw new BadRequestException("can't find driver or lorry");
        }
        lorry.drivers.remove(driver);
        Lorry savedLorry = lorryRepository.saveAndFlush(lorry);
        return lorryMapper.lorryToLorryDto(savedLorry);
    }

    @Override
    public LorryDto saveLorry(LorryDto lorryDto) {
        Lorry lorry = lorryMapper.lorryDtoToLorry(lorryDto);

        if(!numberPlateCheck(lorry.getNumberPlate())) {
            throw new BadRequestException("Number Plate already exist");
        }

        Lorry savedLorry = lorryRepository.saveAndFlush(lorry);

        LorryDto savedLorryDto = lorryMapper.lorryToLorryDto(savedLorry);
        savedLorryDto.setUrl("/api/v1/lorry/" + savedLorryDto.getId());

        return savedLorryDto;
    }

    @Override
    public LorryDto updateLorry(LorryDto lorryDto) {
        Lorry lorry = lorryRepository.findById(lorryDto.getId())
                .orElseThrow(ResourceNotFoundException::new);

        lorryRepository.updateLorryById(lorryDto.getNumberPlate(), lorryDto.getModelNumber(), lorryDto.getManufacturer(),
                lorryDto.getType(), lorry.getId());

        Lorry updatedLorry = lorryRepository.getReferenceById(lorry.getId());

        LorryDto updatedLorryDto = lorryMapper.lorryToLorryDto(updatedLorry);
        updatedLorryDto.setUrl("/api/v1/lorry/" + updatedLorryDto.getId());
        return updatedLorryDto;
    }

}
