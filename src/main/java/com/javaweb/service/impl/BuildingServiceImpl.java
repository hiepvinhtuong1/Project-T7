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
import com.javaweb.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.apache.tomcat.util.codec.binary.Base64;


import javax.transaction.Transactional;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

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
    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        return null;
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, org.springframework.data.domain.Pageable pageable) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest, pageable);

        List<BuildingSearchResponse> buildingReponseDTOs = new ArrayList<>();

        for (BuildingEntity it : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingConverter.convertToResponse(it);
            buildingReponseDTOs.add(buildingSearchResponse);
        }

        return buildingReponseDTOs;
    }


    @Override
    @Transactional
    public void updateAssignmentBuildingById(Long buildingId, List<Long> staffIds) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).orElse(null);
        if (buildingEntity != null) {
            assignmentBuildingRepository.deleteAssignmentBuildingEntitiesByBuildings(buildingEntity);
            buildingEntity.getAssignmentBuildingEntities().clear();
            List<UserEntity> userEntities = userRepository.findAllById(staffIds);
            for (UserEntity userEntity : userEntities) {
                AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
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
        saveThumbnail(buildingDTO, buildingEntity);
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
    public void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {

        String path = "/building/" + buildingDTO.getImageName();

        if (buildingDTO.getImageBase64() != null) {
            File oldFile = new File("C://home/office" + buildingEntity.getImage());
            oldFile.delete();
        }

        String base64Data = buildingDTO.getImageBase64();
        if (base64Data.contains(",")){
            base64Data = base64Data.split(",")[1];
        }

        byte[] bytes = Base64.decodeBase64(base64Data.getBytes(StandardCharsets.UTF_8));
        uploadFileUtils.writeOrUpdate(path, bytes);
        buildingEntity.setImage(path);
    }

    @Override
    @Transactional
    public void deleteBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        if (buildingEntity != null) {

            // xoóa liên ket building trong userentity
            assignmentBuildingRepository.deleteAssignmentBuildingEntitiesByBuildings(buildingEntity);

            // xoa lien ket building trong renareaentity
            rentAreaRepository.deleteAllByBuilding(buildingEntity);

            buildingRepository.delete(buildingEntity);

        }
    }

    @Override
    public int countTotalItems() {
        return buildingRepository.countToTalImtes();
    }


    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
        return buildingDTO;
    }
}
