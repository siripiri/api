package com.transport.sabi.api.dao;

import com.transport.sabi.api.domain.Lorry;
import com.transport.sabi.api.v1.model.LorryDto;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

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
    public Lorry saveOrUpdate(Lorry lorry) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(lorry);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return lorry;
    }
}
