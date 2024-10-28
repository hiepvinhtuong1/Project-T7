package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);

    BuildingDTO findById(Long id);

    public void updateAssignmentBuildingById(Long buildingId, List<Long> staffIds);

    //public void createBuilding(BuildingDTO buildingDTO);

    //public void updateBuilding(BuildingDTO buildingDTO);

    public BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO);

    public void deleteBuildingById(Long id);
}
