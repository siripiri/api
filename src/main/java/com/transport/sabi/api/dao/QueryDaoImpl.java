package com.transport.sabi.api.dao;

import com.transport.sabi.api.services.exception.BadRequestException;
import com.transport.sabi.api.services.exception.ResourceNotFoundException;
import com.transport.sabi.api.v1.mapper.LorryMapper;
import com.transport.sabi.api.v1.model.driverDto.DriverNameDto;
import com.transport.sabi.api.v1.model.location.LocationDtoPost;
import com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Component
@Transactional
public class QueryDaoImpl implements QueryDao {

    Logger logger = LoggerFactory.getLogger(QueryDaoImpl.class.getName());

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
    public Object getLorryAndDriverByLorryId(Long id) {
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.joinTransaction();
            Query query = entityManager.createNativeQuery("select D.ID as DRIVER_ID " +
                    "from LORRY L " +
                    "left join LORRY_DRIVER LD on L.ID = LD.LORRY_ID " +
                    "left join DRIVER D on D.ID=LD.DRIVER_ID " +
                    "where L.ID = ?1");
            query.setParameter(1, id);
            return query.getSingleResult();
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
            Query query = entityManager.createNativeQuery("select D.ID, D.NAME, D.DOB, D.GENDER, D.PHONE_NUMBER1, D.PHONE_NUMBER2," +
                    " D.ADDRESS, D.CITY, D.STATE, D.ZIPCODE, L.ID as LORRY_ID, L.NUMBER_PLATE " +
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

    @Override
    public List<Object[]> getAllTripTable() {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.joinTransaction();
            Query query = entityManager.createNativeQuery("SELECT T.ID, T.DATE, T.PLANT_TO_DISTRIBUTOR, T.DISTRIBUTOR_TO_PLANT," +
                    " T.PLANT_START, T.DES_END, T.DES_START, T.PLANT_END, T.LOCATION_ID, L.DISTRIBUTOR_NAME, L.CITY, " +
                    " L.KM_ALLOCATED, T.LORRY_ID, LO.NUMBER_PLATE, T.DRIVER_ID, D.NAME  " +
                    " FROM TRIP_DETAIL T" +
                    " LEFT JOIN LOCATION L ON T.LOCATION_ID = L.ID" +
                    " LEFT JOIN LORRY LO ON T.LORRY_ID = LO.ID" +
                    " LEFT JOIN DRIVER D ON T.DRIVER_ID = D.ID;");
            return query.getResultList();
        } catch (ResourceNotFoundException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<LorryIdPlateDto> getAllLorryNameAndId() {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.joinTransaction();
            String query = "SELECT NEW com.transport.sabi.api.v1.model.lorry.LorryIdPlateDto(l.id, l.numberPlate) FROM Lorry l";
            TypedQuery<LorryIdPlateDto> typedQuery = entityManager.createQuery(query, LorryIdPlateDto.class);
            return typedQuery.getResultList();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Not Found");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Object[]> getAllLocationTrip() {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.joinTransaction();
            Query query = entityManager.createNativeQuery("SELECT L.ID, L.DISTRIBUTOR_NAME, L.CITY  FROM LOCATION L;");
            return query.getResultList();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Not Found");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<DriverNameDto> getAllDriverName() {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.joinTransaction();
            String query = "SELECT NEW com.transport.sabi.api.v1.model.driverDto.DriverNameDto(d.id, d.name) FROM Driver d";
            TypedQuery<DriverNameDto> typedQuery = entityManager.createQuery(query, DriverNameDto.class);
            logger.info("Fetching drivers details from DB");
            return typedQuery.getResultList();
        } catch (Exception e) {
            throw new BadRequestException("Bad Request");
        } finally {
            entityManager.close();
        }
    }
}
