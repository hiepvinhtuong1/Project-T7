package com.javaweb.api.admin;

import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ReponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {

    @Autowired
    UserService userService;

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
            return ResponseEntity.ok(successDTO);
        }catch (Exception e){
            ReponseDTO errorDTO = new ReponseDTO();
            errorDTO.setMessage("Internal Server Error");
            errorDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }

    @GetMapping("/{id}")
    private Object loadStaffs(@PathVariable Long id){
        ReponseDTO reponseDTO =  userService.loadStaff(id);
        return reponseDTO;
    }

    @DeleteMapping("/{ids}")
    private Object deleteBuilding(@PathVariable String[] ids){
        for (String id : ids) {
            System.out.println("id = " + id);
            buildingService.deleteBuildingById(Long.parseLong(id));
        }
        return  new String("ok");
    }

    @PutMapping("/staffs")
    private Object updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.updateAssignmentBuildingById(assignmentBuildingDTO.getBuildingId(), assignmentBuildingDTO.getStaffs());
        return  new String("ok");
    }

}
