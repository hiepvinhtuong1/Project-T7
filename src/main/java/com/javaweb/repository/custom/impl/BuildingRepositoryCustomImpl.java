package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.ObjectUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b");
        StringBuilder join = new StringBuilder(" ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");

        handleJoinClauses(buildingSearchRequest, join);
        handleNormalWhereClauses(buildingSearchRequest, where);
        handleSpeciallWhereClauses(buildingSearchRequest, where);
        sql.append(join).append(where);
        sql.append(" GROUP BY b.id");
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    public void handleJoinClauses(BuildingSearchRequest buildingSearchBuilder, StringBuilder join) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            join.append(" JOIN assignmentbuilding ab ON ab.buildingid = b.id");
        }
//        List<String> typeCode = buildingSearchBuilder.getTypeCode();
//        if (typeCode != null && typeCode.size() != 0) {
//            join.append(" JOIN buildingrenttype brt ON brt.buildingid = b.id JOIN renttype rt ON brt.renttypeid = rt.id");
//        }
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        if ((rentAreaFrom != null) || (rentAreaTo != null)) {
            join.append(" JOIN rentarea ra ON ra.buildingid = b.id");
        }

    }

    public void handleNormalWhereClauses(BuildingSearchRequest buildingSearchBuilder, StringBuilder where) {
        try {
            Class myClass = buildingSearchBuilder.getClass();
            Field[] allFields = myClass.getDeclaredFields();
            for (Field field : allFields) {
                field.setAccessible(true);

                if (!field.getName().equals("staffId") && !field.getName().equals("typeCode")
                        && !field.getName().startsWith("rentPrice") && !field.getName().startsWith("area")) {
                    Object value = field.get(buildingSearchBuilder);
                    if (field.getType().equals(String.class) && ObjectUtils.check(value)) {
                        where.append(" AND b." + field.getName() + " LIKE '%" + value + "%'");
                    } else if (value != null && ObjectUtils.check(value)) {
                        where.append(" AND b." + field.getName() + " = " + value);
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void handleSpeciallWhereClauses(BuildingSearchRequest buildingSearchBuilder, StringBuilder where) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" AND ab.staffId = " + staffId);
        }

        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        if (rentAreaFrom != null || rentAreaTo != null) {
            if (rentAreaFrom != null) {
                where.append(" AND ra.value >= " + rentAreaFrom);
            }

            if (rentAreaTo != null) {
                where.append(" AND ra.value <= " + rentAreaTo);
            }
        }

        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (rentPriceFrom != null || rentPriceTo != null) {
            if (rentPriceFrom != null) {
                where.append(" AND b.rentprice >= " + rentPriceFrom);
            }

            if (rentPriceTo != null) {
                where.append(" AND b.rentprice < = " + rentPriceTo);
            }
        }

        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0) {
            for (String type : typeCode) {
                where.append(" AND b.type " + " LIKE '%" + type + "%'");
            }
        }
    }

}
