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
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItems(buildingSearchRequest);
    }

    @Override
    @Transactional
    public void updateAssignmentBuildingById(Long buildingId, List<Long> staffIds) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).orElse(null);
        if (buildingEntity != null) {
            List<UserEntity> userEntities = userRepository.findByIdIn(staffIds);
            buildingEntity.setUsers(userEntities);
            buildingRepository.save(buildingEntity);
        }
    }

    @Override
    @Transactional
    public BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = new BuildingEntity();
        if (buildingDTO.getId() != null) {
            BuildingEntity buildingEntityOld = buildingRepository.findById(buildingDTO.getId()).orElse(null);

            if (buildingEntityOld.getImage() != null && !buildingEntityOld.getImage().isEmpty()) {
                buildingEntity.setImage(buildingEntityOld.getImage());
            }
        }
        buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        saveThumbnail(buildingDTO, buildingEntity);
        rentAreaRepository.saveAll(buildingEntity.getRentAreaEntities());
        return buildingRepository.save(buildingEntity);
    }

    @Override
    public void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

    @Override
    public boolean checkUserOfBuilding(Long userId, Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).orElse(null);
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (buildingEntity.getUsers().contains(userEntity)) {
            return true;
        }
        return false;
    }

    @Override
        @Transactional
        public void deleteBuildingById (Long id){
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
        @Transactional
        public void deleteBuildingByIds (List < Long > ids) {
            List<BuildingEntity> buildingEntities = buildingRepository.findAllById(ids);
            if (!buildingEntities.isEmpty()) {
                for (BuildingEntity buildingEntity : buildingEntities) {
                    // lay nhung thang quan li toa nha va xoa toa nha khoi nhung thang quan li nay
                    for (UserEntity userEntity : buildingEntity.getUsers()) {
                        userEntity.getBuildings().remove(buildingEntity);
                        userRepository.save(userEntity);
                    }

                    // xoa tat ca nhung thang quan li khoi toa nha
                    buildingEntity.getUsers().clear();
                }
            }
            buildingRepository.deleteByIdIn(ids);
        }


        @Override
        public BuildingDTO findById (Long id){
            BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
            BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
            return buildingDTO;
        }
    }
