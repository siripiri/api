package com.transport.sabi.api.services;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.transport.sabi.api.dao.QueryDao;
import com.transport.sabi.api.domain.driver.Driver;
import com.transport.sabi.api.domain.driver.EmergencyContact;
import com.transport.sabi.api.domain.driver.FamilyInformation;
import com.transport.sabi.api.domain.driver.PersonalInformation;
import com.transport.sabi.api.repository.DriverRepository;
import com.transport.sabi.api.repository.EmergencyContactRepository;
import com.transport.sabi.api.repository.FamilyInformationRepository;
import com.transport.sabi.api.repository.PersonalInformationRepository;
import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import com.transport.sabi.api.v1.mapper.DriverMapper;
import com.transport.sabi.api.v1.model.driverDto.DriverDto;
import com.transport.sabi.api.v1.model.driverDto.DriverFormDto;
import com.transport.sabi.api.v1.model.driverDto.DriverNameDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    Logger log = LoggerFactory.getLogger(DriverServiceImpl.class);

    private final QueryDao queryDao;
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final PersonalInformationRepository personalInformationRepository;
    private final EmergencyContactRepository emergencyContactRepository;
    private final FamilyInformationRepository familyInformationRepository;

    public DriverServiceImpl(QueryDao queryDao, DriverRepository driverRepository, DriverMapper driverMapper,
                             PersonalInformationRepository personalInformationRepository,
                             EmergencyContactRepository emergencyContactRepository,
                             FamilyInformationRepository familyInformationRepository) {
        this.queryDao = queryDao;
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
        this.personalInformationRepository = personalInformationRepository;
        this.emergencyContactRepository = emergencyContactRepository;
        this.familyInformationRepository = familyInformationRepository;
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
        driver.setGender(driverDto.getGender());
        driver.setPhoneNumber1(driverDto.getPhoneNumber1());
        driver.setPhoneNumber2(driverDto.getPhoneNumber1());
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

    @Override
    public DriverFormDto getDriverFormDtoById(Long id) {
        Driver driver = driverRepository.findById(id).orElse(null);
        if(driver == null) return null;

        DriverFormDto driverFormDto = driverMapper.driverToDriverFormDto(driver);
        driverFormDto.setAddress(driver.getAddress());
        driverFormDto.setUrl("/api/v1/driver/" + driverFormDto.getProfile().getId());
        return driverFormDto;
    }

    @Override
    public DriverFormDto saveDriverFormDto(DriverFormDto driverFormDto) {

        // Driver
        Driver driver = driverMapper.driverFormDtoToDriver(driverFormDto);

        // Address
        driver.setAddress(driverFormDto.getAddress());

        // Personal Information
        PersonalInformation personalInformation = driverMapper.
                personalInformationDtoToPersonalInformation(driverFormDto.getPersonalInformation());
        personalInformation.setDriver(driver);

        // Emergency Contact
        Set<EmergencyContact> emergencyContacts = driverFormDto.getEmergencyContacts().stream()
                .map(driverMapper::emergencyContactDtoToEmergencyContact)
                .peek(driver::addEmergencyContact)
                .collect(Collectors.toSet());

        // Family Information
        Set<FamilyInformation> familyInformations = driverFormDto.getFamilyInformations().stream()
                .map(driverMapper::familyInformationDtoToFamilyInformation)
                .peek(driver::addFamilyInformation)
                .collect(Collectors.toSet());

        // Relationship
        driver.setPersonalInformation(personalInformation);

        Driver savedDriver = driverRepository.save(driver);
        DriverFormDto savedDriverFormDto = driverMapper.driverToDriverFormDto(savedDriver);
        savedDriverFormDto.setUrl("/api/v1/driver/" + savedDriverFormDto.getProfile().getId());
        return savedDriverFormDto;
    }

    @Override
    public List<DriverNameDto> getAllDriverName() {
        List<DriverNameDto> driverNameDtos = queryDao.getAllDriverName();
        log.info(driverNameDtos.toString());
        if(driverNameDtos.isEmpty()){
            log.info("No record found in DB");
            throw new ResourceNotFoundException("Not Found");
        }
        return driverNameDtos;
    }

}
