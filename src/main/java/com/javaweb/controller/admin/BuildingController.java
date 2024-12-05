package com.javaweb.controller.admin;


import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.buildingRentType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.List;

@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/admin/building-list")
    private ModelAndView buildingList(@ModelAttribute(name = "modelSearch") BuildingSearchRequest params,
                                      HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("district", DistrictCode.type());
        modelAndView.addObject("renttype", buildingRentType.type());
        modelAndView.addObject("staffs", userService.listStaff());

        //Neu tai khoan la user thi phai xet them staff id
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            params.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        // xuong db lay data
        BuildingSearchResponse model = new BuildingSearchResponse();
        DisplayTagUtils.of(request, model);
        List<BuildingSearchResponse> buildingSearchResponses = buildingService.findAll(params, PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        // roi quang ra view
        model.setListResult(buildingSearchResponses);
        model.setTotalItems(buildingService.countTotalItems(params));
        modelAndView.addObject("model", model);
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
        if (buildingService.checkUserOfBuilding(SecurityUtils.getPrincipal().getId(), id)
                || SecurityUtils.getAuthorities().contains("ROLE_MANAGER")) {
            BuildingDTO buildingDTO = buildingService.findById(id);
            modelAndView.addObject("district", DistrictCode.type());
            modelAndView.addObject("renttype", buildingRentType.type());
            //xuong Db dung ham findById de lay data toa nha hien tai => BuildingENtity convert BuildingDTO
            modelAndView.addObject("buildingEdit", buildingDTO);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/login?accessDenied");
        }
    }
}
