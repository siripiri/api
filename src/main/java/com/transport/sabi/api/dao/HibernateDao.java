package com.transport.sabi.api.dao;

import com.transport.sabi.api.domain.Lorry;

public interface HibernateDao {
    Lorry saveOrUpdate(Lorry lorry);
}
