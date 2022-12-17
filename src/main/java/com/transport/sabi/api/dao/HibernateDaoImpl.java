package com.transport.sabi.api.dao;

import com.transport.sabi.api.domain.repository.LorryRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

@Component
@Transactional
public class HibernateDaoImpl implements HibernateDao {
    private final EntityManagerFactory entityManagerFactory;
    private final LorryRepository lorryRepository;

    public HibernateDaoImpl(EntityManagerFactory entityManagerFactory,
                            LorryRepository lorryRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.lorryRepository = lorryRepository;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
