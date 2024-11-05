package com.javaweb.controller.admin;


import com.javaweb.enums.buildingRentType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ReponseDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/admin/building-list")
    private ModelAndView buildingList(@ModelAttribute(name = "modelSearch") BuildingSearchRequest params, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("district", DistrictCode.type());
        modelAndView.addObject("renttype", buildingRentType.type());
        modelAndView.addObject("staffs", userService.listStaff());
        DisplayTagUtils.of(request, params);
        // xuong db lay data
        List<BuildingSearchResponse> buildingSearchResponses = buildingService.findAll(params, (Pageable) PageRequest.of(params.getPage()-1, params.getMaxPageItems()));
        // roi quang ra view
        params.setListResult(buildingSearchResponses);
        params.setTotalItem(buildingService.countTotalItems());
        modelAndView.addObject("listBuilding", params);
        return modelAndView;
    }

    @GetMapping("/admin/building-edit")
    private ModelAndView buildingEdit(@ModelAttribute(name = "buildingEdit") BuildingDTO params) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("district", DistrictCode.type());
        modelAndView.addObject("renttype", buildingRentType.type());
        return modelAndView;
    }

    @GetMapping("/admin/building-edit-{id}")
    private ModelAndView buildingEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findById(id);
        modelAndView.addObject("district", DistrictCode.type());
        modelAndView.addObject("renttype", buildingRentType.type());
        //xuong Db dung ham findById de lay data toa nha hien tai => BuildingENtity convert BuildingDTO
        modelAndView.addObject("buildingEdit", buildingDTO);
        return modelAndView;
    }
}
