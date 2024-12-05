package com.javaweb.controller.admin;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.Status;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.buildingRentType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.TransactionService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.javaweb.enums.Status.getStatusByValue;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ITransactionService transactionService;


    @GetMapping("/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute(name = "modelSearch") CustomerDTO params,
                                     HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("admin/customer/list");
        modelAndView.addObject("status", Status.type());
        modelAndView.addObject("staffs", userService.listStaff());
        CustomerDTO model = new CustomerDTO();
        DisplayTagUtils.of(request, model);

        //Neu tai khoan la user thi phai xet them staff id
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            params.setStaffId(SecurityUtils.getPrincipal().getId());
        }

        params.setIs_active(Long.parseLong("1"));
        List<CustomerDTO> listCustomer = customerService.findAll(params, PageRequest.of(model.getPage()-1,model.getMaxPageItems()));
        for (CustomerDTO customerDTO : listCustomer) {
            Status status = getStatusByValue(customerDTO.getStatus());
            String statusName = status.getStatusName();
            customerDTO.setStatus(statusName);
        }
        model.setListResult(listCustomer);
        model.setTotalItems(customerService.countTotalItems(params));
        modelAndView.addObject("model", model);
        return modelAndView;
    }

    @GetMapping("/admin/customer-edit")
    private ModelAndView customerEdit(@ModelAttribute(name = "customerEdit") CustomerDTO params) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
        modelAndView.addObject("status", Status.type());
        return modelAndView;
    }

    @GetMapping("/admin/customer-edit-{id}")
    private ModelAndView customerEdit(@PathVariable Long id) {

            if (customerService.checkUserOfCustomer(SecurityUtils.getPrincipal().getId(), id)
                || SecurityUtils.getAuthorities().contains("ROLE_MANAGER")) {
                ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
                CustomerDTO customerDTO = customerService.findById(id);
                if (customerDTO.getIs_active() == 0) {
                    return new ModelAndView("redirect:/admin/customer-list");
                }
                modelAndView.addObject("status", Status.type());
                modelAndView.addObject("transactionType", TransactionType.type());
                //xuong Db dung ham findById de lay data toa nha hien tai => BuildingENtity convert BuildingDTO
                modelAndView.addObject("customerEdit", customerDTO);

                List<TransactionDTO> transactionCSKH = transactionService.getListTransaction("CSKH",id);
                List<TransactionDTO> transactionDDX = transactionService.getListTransaction("DDX",id);

                modelAndView.addObject("transactionCSKH", transactionCSKH);
                modelAndView.addObject("transactionDDX", transactionDDX);
                return modelAndView;
            } else {
                return new ModelAndView("redirect:/login?accessDenied");
            }

    }

}
