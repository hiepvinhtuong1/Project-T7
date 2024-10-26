package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
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
        if (buildingEntity == null) {

        } else {
            for (UserEntity userEntity : buildingEntity.getUsers()) {
                //xoa lien ket voi building trong UserEntity
                userEntity.getBuildings().remove(buildingEntity);
                userRepository.save(userEntity);
            }

            // Xoa lien ket voi tat ca UserEntity trong buiildings
            buildingEntity.getUsers().clear();
            buildingRepository.save(buildingEntity);
        }

        for (Long staffId : staffIds) {
            UserEntity userEntity = userRepository.findById(staffId).orElse(null);
            if (userEntity != null) {

                // them user vao danh sach quan li cua building
                if (!buildingEntity.getUsers().contains(userEntity)) {
                    buildingEntity.getUsers().add(userEntity);
                }

                // them building vao danh sach cua user neu chua co
                if (!userEntity.getBuildings().contains(buildingEntity)) {
                    userEntity.getBuildings().add(buildingEntity);
                    userRepository.save(userEntity);
                }
            }
            buildingRepository.save(buildingEntity);
        }
    }

    @Override
    @Transactional
    public void createOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = new BuildingEntity();
        if (buildingDTO.getId() != null) {
            buildingEntity = buildingRepository.findById(buildingDTO.getId()).orElse(null);
            if (buildingEntity != null) {
                rentAreaRepository.deleteAllByBuilding(buildingEntity);
            }
        }
        String[] rentArea = buildingDTO.getRentArea().split(",");
        for (int i = 0; i < rentArea.length; i++) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(Long.parseLong(rentArea[i]));
            rentAreaRepository.save(rentAreaEntity);
            buildingEntity.getRentAreaEntities().add(rentAreaEntity);
        }
        buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        buildingRepository.save(buildingEntity);
    }

    @Override
    @Transactional
    public void deleteBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        if (buildingEntity != null) {

            // xoóa liên ket building trong userentity
            for (UserEntity userEntity : buildingEntity.getUsers()) {
                userEntity.getBuildings().remove(buildingEntity);
                userRepository.save(userEntity);
            }

            // xoa lien ket voi tat ca userentity trong buildingentity
            buildingEntity.getUsers().clear();

            // xoa lien ket building trong renareaentity
            rentAreaRepository.deleteAllByBuilding(buildingEntity);

            buildingRepository.delete(buildingEntity);

        }
    }
}
