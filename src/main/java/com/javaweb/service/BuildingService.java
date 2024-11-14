package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {

    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable);

    BuildingDTO findById(Long id);

    public void updateAssignmentBuildingById(Long buildingId, List<Long> staffIds);

    public BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO);

    public void deleteBuildingById(Long id);

    public void deleteBuildingByIds(List<Long> ids);

    int countTotalItems(BuildingSearchRequest buildingSearchRequest);

    public void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity);
}
