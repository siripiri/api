package com.transport.sabi.api.dao;


import com.transport.sabi.api.domain.driver.Driver;

public interface HibernateDao {
    Driver saveDriver(Driver driver);
}
