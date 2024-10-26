package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.javaweb.enums.DistrictCode.getdistrictCodeByValue;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse convertToResponse(BuildingEntity it) {
        BuildingSearchResponse buildingReponseDTO = modelMapper.map(it, BuildingSearchResponse.class);
        DistrictCode districtCode = getdistrictCodeByValue(it.getDistrict());
        String districtName = "";
        if (districtCode != null) districtName = districtCode.getDistrictName();
        buildingReponseDTO.setAddress(it.getStreet() + "," + it.getWard() + "," + districtName);
        String rentArea = it.getRentAreaEntities().stream().map(i -> i.getValue().toString()).collect(Collectors.joining(","));
        buildingReponseDTO.setRentArea(rentArea);
        return buildingReponseDTO;
    }

    public BuildingDTO convertToDTO(BuildingEntity it) {
        BuildingDTO buildingDTO = modelMapper.map(it, BuildingDTO.class);
        String[] types = it.getType().split(",");
        List<String> typeCode = Arrays.asList(types);
        buildingDTO.setTypeCode(typeCode);
        String rentArea = it.getRentAreaEntities().stream().map(i -> i.getValue().toString()).collect(Collectors.joining(","));
        buildingDTO.setRentArea(rentArea);
        return buildingDTO;
    }

    public BuildingEntity   convertToEntity(BuildingDTO dto) {
        BuildingEntity buildingEntity = modelMapper.map(dto, BuildingEntity.class);
        List<String> typeCode = dto.getTypeCode();
        String types = typeCode.stream().collect(Collectors.joining(","));

        buildingEntity.setType(types);
        return buildingEntity;
    }
}
