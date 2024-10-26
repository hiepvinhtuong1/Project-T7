package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;

import java.util.List;

public interface BuildingRepositoryCustom {
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest);
    public void updateAssignmentBuildingById(Long buildingId, List<Long> staffIds);
}
