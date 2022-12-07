package com.transport.sabi.api.services;

import com.transport.sabi.api.dao.QueryDao;
import com.transport.sabi.api.domain.repository.DriverRepository;
import com.transport.sabi.api.v1.mapper.DriverMapper;
import com.transport.sabi.api.v1.model.DriverNamesWithLorryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    private final QueryDao queryDao;
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    public DriverServiceImpl(QueryDao queryDao, DriverRepository driverRepository, DriverMapper driverMapper) {
        this.queryDao = queryDao;
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    @Override
    public List<DriverNamesWithLorryDto> getDriversNameAndLorry() {
        return queryDao.getDriversNameAndLorry()
                .stream()
                .map(driverMapper::driverNamesWithLorryId)
                .map(driverNamesWithLorryDto -> {
                    driverNamesWithLorryDto.setUrl("/api/v1/driver/names/" + driverNamesWithLorryDto.getDriverId());
                    return driverNamesWithLorryDto;
                }).collect(Collectors.toList());
    }
}
