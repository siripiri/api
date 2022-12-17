package com.transport.sabi.api.services;

import com.transport.sabi.api.dao.HibernateDao;
import com.transport.sabi.api.dao.QueryDao;
import com.transport.sabi.api.domain.Driver;
import com.transport.sabi.api.domain.repository.DriverRepository;
import com.transport.sabi.api.domain.repository.LocationRepository;
import com.transport.sabi.api.domain.repository.LorryRepository;
import com.transport.sabi.api.services.exception.BadRequestException;
import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import com.transport.sabi.api.v1.mapper.DriverMapper;
import com.transport.sabi.api.v1.model.DriverDto;
import com.transport.sabi.api.v1.model.LorryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    private final QueryDao queryDao;
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final LorryRepository lorryRepository;
    private final HibernateDao hibernateDao;
    private final LocationRepository locationRepository;

    public DriverServiceImpl(QueryDao queryDao, DriverRepository driverRepository, DriverMapper driverMapper,
                             LorryRepository lorryRepository, HibernateDao hibernateDao,
                             LocationRepository locationRepository) {
        this.queryDao = queryDao;
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
        this.lorryRepository = lorryRepository;
        this.hibernateDao = hibernateDao;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<DriverDto> getDriversNameAndLorry() {
        return queryDao.getDriversNameAndLorry()
                .stream()
                .map(driverMapper::driverNamesWithLorryId)
                .map(driverNamesWithLorryDto -> {
                    driverNamesWithLorryDto.setUrl("/api/v1/driver/names/" + driverNamesWithLorryDto.getId());
                    return driverNamesWithLorryDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<DriverDto> getDriversWithLorry() {
        return queryDao.getDriversWithLorry()
                .stream()
                .map(driverMapper::driversWithLorry)
                .map(driverDto -> {
                    driverDto.setUrl("/api/v1/driver/lorry/" + driverDto.getId());
                    return driverDto;
                }).collect(Collectors.toList());
    }

    @Override
    public DriverDto save(DriverDto driverDto) {
        Driver driver = this.driverMapper.driverDtoToDriver(driverDto);
        Driver savedDriver = this.driverRepository.saveAndFlush(driver);

        DriverDto savedDriverDto = this.driverMapper.driverToDriverDto(savedDriver);
        savedDriverDto.setUrl("/api/v1/driver/" + savedDriverDto.getId());

        return savedDriverDto;
    }

    @Override
    public DriverDto update(DriverDto driverDto) {
        Driver driver = driverRepository.findById(driverDto.getId())
                .orElseThrow(ResourceNotFoundException::new);

        driver.setAddress(driverDto.getAddress());
        driver.setName(driverDto.getDriverName());
        driver.setChildrenDetails(driverDto.getChildrenDetails());
        driver.setDob(driverDto.getDob());

        Driver updatedDriver = driverRepository.save(driver);
        DriverDto updatedDriverDto = driverMapper.driverToDriverDto(updatedDriver);
        updatedDriverDto.setUrl("/api/v1/driver/" + driver.getId());
        return updatedDriverDto;
    }

    @Override
    public DriverDto getLorryDtoById(Long id) {
        return this.driverRepository.findById(id)
                .map(driverMapper::driverToDriverDto)
                .map(driverDto -> {
                    driverDto.setUrl("api/v1/driver/" + driverDto.getId());
                    return driverDto;
                })
                .orElseThrow(ResourceNotFoundException::new);

    }

}
