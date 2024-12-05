package com.javaweb.api.admin;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ReponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {

    @Autowired
    IUserService userService;

    @Autowired
    BuildingService buildingService;

    @PostMapping
    private ResponseEntity<?> createOrUpdateBuilding(@RequestBody @Valid BuildingDTO buildingDTO,
                                                     BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).collect(Collectors.toList());
                ReponseDTO reponseDTO = new ReponseDTO();
                reponseDTO.setMessage("Field error");
                reponseDTO.setDetail(errorMessages);
                return ResponseEntity.badRequest().body(reponseDTO);
            }
            buildingService.createOrUpdateBuilding(buildingDTO);
            ReponseDTO successDTO = new ReponseDTO();
            successDTO.setMessage("Success");
            return ResponseEntity.status(HttpStatus.CREATED).body(successDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
            ReponseDTO errorDTO = new ReponseDTO();
            errorDTO.setMessage("Internal Server Error");
            errorDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }

    @GetMapping("/{id}")
    private Object loadStaffs(@PathVariable Long id){
        ReponseDTO reponseDTO =  userService.loadStaff(id, Long.parseLong("1"));
        return reponseDTO;
    }

    @DeleteMapping("/{ids}")
    private Object deleteBuilding(@PathVariable List<Long> ids){
        ReponseDTO reponseDTO = new ReponseDTO();
        try {

            if (ids.size() == 0){
                reponseDTO.setMessage("Fail");
                reponseDTO.setDetail(Collections.singletonList("ids is empty"));
                return ResponseEntity.badRequest().body(reponseDTO);
            }

            buildingService.deleteBuildingByIds(ids);

            reponseDTO.setMessage("Success");
            return ResponseEntity.ok().body(reponseDTO);
        } catch (Exception e) {
            reponseDTO.setMessage("Internal Server Error");
            reponseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponseDTO);
        }
    }

    @PutMapping("/staffs")
    private Object updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.updateAssignmentBuildingById(assignmentBuildingDTO.getBuildingId(), assignmentBuildingDTO.getStaffs());
        return  new String("ok");
    }

}
