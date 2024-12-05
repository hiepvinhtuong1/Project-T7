package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerCustom {
    List<CustomerEntity> findAll(CustomerDTO request, Pageable pageable);

    int countTotalItems(CustomerDTO request);

}
