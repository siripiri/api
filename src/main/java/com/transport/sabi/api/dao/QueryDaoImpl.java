package com.transport.sabi.api.dao;

import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import com.transport.sabi.api.v1.mapper.LorryMapper;
import com.transport.sabi.api.v1.model.LocationDtoPost;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Component
@Transactional
public class QueryDaoImpl implements QueryDao {

    private final EntityManagerFactory entityManagerFactory;
    private final LorryMapper lorryMapper;

    public QueryDaoImpl(EntityManagerFactory entityManagerFactory, LorryMapper lorryMapper) {
        this.entityManagerFactory = entityManagerFactory;
        this.lorryMapper = lorryMapper;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public int updateLocation(LocationDtoPost locationDtoPost, Long id) {
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.joinTransaction();
            Query query = entityManager.createNativeQuery("UPDATE Location SET " +
                            "address=?1, " +
                            "city=?2, " +
                            "state=?3, " +
                            "zipcode=?4, " +
                            "distributor_name=?5, " +
                            "km_allocated=?6 " +
                    "WHERE id=?7");
            query.setParameter(1, locationDtoPost.getAddress().getAddress());
            query.setParameter(2, locationDtoPost.getAddress().getCity());
            query.setParameter(3, locationDtoPost.getAddress().getState());
            query.setParameter(4, locationDtoPost.getAddress().getZipcode());
            query.setParameter(5, locationDtoPost.getDistributorName());
            query.setParameter(6, locationDtoPost.getKmAllocated());
            query.setParameter(7, id);
            return query.executeUpdate();
        } catch (Exception e) {
            return -1;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Object[]> getAllLorryAndDriverName() {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.joinTransaction();
            Query query = entityManager.createNativeQuery("select L.ID as LORRY_ID, L.NUMBER_PLATE, L.TYPE, L.MODEL_NUMBER, L.MANUFACTURER, D.NAME " +
                    "from LORRY L " +
                    "left join LORRY_DRIVER LD on L.ID=LD.LORRY_ID " +
                    "left join DRIVER D on LD.DRIVER_ID=D.ID;");
            return (List<Object[]>) query.getResultList();
        } catch (ResourceNotFoundException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Object[]> getDriversNameAndLorry() {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.joinTransaction();
            Query query = entityManager.createNativeQuery("select L.ID as LORRY_ID, D.ID as DRIVER_ID, D.NAME as DRIVER_NAME " +
                    "from DRIVER D " +
                    "left join LORRY_DRIVER LD on D.ID = LD.DRIVER_ID " +
                    "left join LORRY L on L.ID=LD.LORRY_ID;");
            return (List<Object[]>) query.getResultList();
        } catch (ResourceNotFoundException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Object[]> getDriversWithLorry() {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.joinTransaction();
            Query query = entityManager.createNativeQuery("select D.ID, D.NAME, D.DOB, D.CHILDREN_DETAILS, D.ADDRESS, D.CITY, D.STATE, " +
                    "D.ZIPCODE, L.ID as LORRY_ID, L.NUMBER_PLATE " +
                    "from DRIVER D " +
                    "left join LORRY_DRIVER LD on D.ID = LD.DRIVER_ID " +
                    "left join LORRY L on L.ID=LD.LORRY_ID;");
            return (List<Object[]>) query.getResultList();
        } catch (ResourceNotFoundException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int insertAssignDriver(Long driverId, Long lorryId) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.joinTransaction();
            Query query = entityManager.createNativeQuery("insert into " +
                    "LORRY_DRIVER (LORRY_ID, DRIVER_ID) " +
                    "VALUES (?1, ?2);");
            query.setParameter(1, lorryId);
            query.setParameter(2, driverId);
            return query.executeUpdate();
        } catch (Exception e) {
            return -1;
        } finally {
            entityManager.close();
        }
    }
}
