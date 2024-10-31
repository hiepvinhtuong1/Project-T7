package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {

        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest);

        List<BuildingSearchResponse> buildingReponseDTOs = new ArrayList<>();

        for (BuildingEntity it : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingConverter.convertToResponse(it);
            buildingReponseDTOs.add(buildingSearchResponse);
        }

        return buildingReponseDTOs;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
        return buildingDTO;
    }

    @Override
    @Transactional
    public void updateAssignmentBuildingById(Long buildingId, List<Long> staffIds) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).orElse(null);
        if (buildingEntity != null) {
            assignmentBuildingRepository.deleteAssignmentBuildingEntitiesByBuildings(buildingEntity.getAssignmentBuildingEntities());
            buildingEntity.getAssignmentBuildingEntities().clear();
            List<UserEntity> userEntities = userRepository.findAllById(staffIds);
            for (UserEntity userEntity : userEntities) {
                AssignmentBuildingEntity assignmentBuildingEntity = new    AssignmentBuildingEntity();
                assignmentBuildingEntity.setBuildings(buildingEntity);
                assignmentBuildingEntity.setStaffs(userEntity);
                assignmentBuildingRepository.save(assignmentBuildingEntity);
            }
        }
    }

    @Override
    @Transactional
    public BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = new BuildingEntity();
        if (buildingDTO.getId() != null) {
            buildingEntity = buildingRepository.findById(buildingDTO.getId()).orElse(null);
            if (buildingEntity != null) {
                rentAreaRepository.deleteAllByBuilding(buildingEntity);
            }
        }
        buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        String[] rentArea = buildingDTO.getRentArea().split(",");
        for (int i = 0; i < rentArea.length; i++) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(Long.parseLong(rentArea[i]));
            rentAreaRepository.save(rentAreaEntity);
            buildingEntity.getRentAreaEntities().add(rentAreaEntity);
        }
        return buildingRepository.save(buildingEntity);
    }

    @Override
    @Transactional
    public void deleteBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        if (buildingEntity != null) {

            // xoóa liên ket building trong userentity
            for (AssignmentBuildingEntity assignmentBuildingEntity : buildingEntity.getAssignmentBuildingEntities()) {
                UserEntity userEntity = assignmentBuildingEntity.getStaffs();
                assignmentBuildingRepository.delete(assignmentBuildingEntity);
                userEntity.getAssignmentBuildingEntities().remove(assignmentBuildingEntity);
                userRepository.save(userEntity);
            }

            // xoa lien ket building trong renareaentity
            rentAreaRepository.deleteAllByBuilding(buildingEntity);

            buildingRepository.delete(buildingEntity);

        }
    }
}
