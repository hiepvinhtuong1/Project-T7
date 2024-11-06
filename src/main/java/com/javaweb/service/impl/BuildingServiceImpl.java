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
import org.apache.tomcat.util.codec.binary.Base64;


import javax.transaction.Transactional;
import java.io.File;
import java.nio.charset.StandardCharsets;
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

            // lay nhung thang quan li toa nha va xoa toa nha khoi nhung thang quan li nay
            for (UserEntity userEntity : buildingEntity.getUsers()) {
                userEntity.getBuildings().remove(buildingEntity);
            }

            // xoa tat ca nhung thang quan li khoi toa nha
            buildingEntity.getUsers().clear();

            // lay nhung thang duoc giao quan li toa nha nay
            List<UserEntity> userEntities = userRepository.findAllById(staffIds);

            // gan tat ca nhung thang quan li nay vao toa nha
            buildingEntity.setUsers(userEntities);

            // gan toa nha nay cho nhung thang quan li
            for (UserEntity userEntity : userEntities) {
                userEntity.getBuildings().add(buildingEntity);
            }

            buildingRepository.save(buildingEntity);
            userRepository.saveAll(userEntities);
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
        if (base64Data.contains(",")) {
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

            // lay nhung thang quan li toa nha va xoa toa nha khoi nhung thang quan li nay
            for (UserEntity userEntity : buildingEntity.getUsers()) {
                userEntity.getBuildings().remove(buildingEntity);
                userRepository.save(userEntity);
            }

            // xoa tat ca nhung thang quan li khoi toa nha
            buildingEntity.getUsers().clear();

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
