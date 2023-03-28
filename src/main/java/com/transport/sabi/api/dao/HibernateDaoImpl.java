package com.transport.sabi.api.dao;

import com.transport.sabi.api.domain.driver.Driver;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;


@Component
@Transactional
public class HibernateDaoImpl implements HibernateDao {
    private final EntityManagerFactory entityManagerFactory;

    public HibernateDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public Driver saveDriver(Driver driver) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(driver);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return driver;
    }
}
