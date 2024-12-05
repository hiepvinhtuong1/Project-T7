package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.CustomerCustom;
import com.javaweb.utils.ObjectUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomerCustomImpl implements CustomerCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity> findAll(CustomerDTO request, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer c");
        StringBuilder join = new StringBuilder(" ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");

        handleJoinClauses(request, join);
        handleNormalWhereClauses(request, where);
        handleSpeciallWhereClauses(request, where);
        sql.append(join).append(where);
        sql.append(" GROUP BY c.id").append(" LIMIT ").append(pageable.getPageSize()).append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        System.out.println(sql);
        return query.getResultList();
    }

    @Override
    public int countTotalItems(CustomerDTO request) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer c");
        StringBuilder join = new StringBuilder(" ");
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");

        handleJoinClauses(request, join);
        handleNormalWhereClauses(request, where);
        handleSpeciallWhereClauses(request, where);
        sql.append(join).append(where);
        sql.append(" GROUP BY c.id");
        System.out.println(sql.toString());
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }

    public void handleJoinClauses(CustomerDTO request, StringBuilder join) {
        Long staffId = request.getStaffId();
        if (staffId != null) {
            join.append(" JOIN assignmentcustomer ac ON ac.customerid = c.id");
        }
    }

    public void handleNormalWhereClauses(CustomerDTO request, StringBuilder where) {
        try {
            Class myClass = request.getClass();
            Field[] allFields = myClass.getDeclaredFields();
            for (Field field : allFields) {
                field.setAccessible(true);

                if (!field.getName().equals("staffId") && !field.getName().equals("managementStaff")) {
                    Object value = field.get(request);
                    if (field.getType().equals(String.class) && ObjectUtils.check(value)) {
                        where.append(" AND c." + field.getName() + " LIKE '%" + value + "%'");
                    } else if (value != null && ObjectUtils.check(value)) {
                        where.append(" AND c." + field.getName() + " = " + value);
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void handleSpeciallWhereClauses(CustomerDTO request, StringBuilder where) {
        Long staffId = request.getStaffId();
        if (staffId != null) {
            where.append(" AND ac.staffId = " + staffId);
        }
    }

}
