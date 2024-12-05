package com.javaweb.api.admin;

import com.javaweb.exception.MyException;
import com.javaweb.model.dto.*;
import com.javaweb.model.response.ReponseDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.CustomerService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IUserService userService;

    @PostMapping
    private ResponseEntity<?> createOrUpdateCustomer(@RequestBody @Valid CustomerDTO customerDTO,
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

            String username = (null != SecurityUtils.getPrincipal() )
                    ? SecurityUtils.getPrincipal().getUsername()
                    : "guest"; // Tên mặc định nếu chưa đăng nhập

            customerDTO.setCreatedBy(username);

            customerDTO.setCreatedDate(new Date());

            customerService.createOrUpdateCustomer(customerDTO);
            ReponseDTO successDTO = new ReponseDTO();
            successDTO.setMessage("Success");
            return ResponseEntity.status(HttpStatus.CREATED).body(successDTO);
        }catch (MyException e){
            ReponseDTO errorDTO = new ReponseDTO();
            errorDTO.setMessage("Field error");
            errorDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.badRequest().body(errorDTO);
        }
        catch (Exception e){
            ReponseDTO errorDTO = new ReponseDTO();
            errorDTO.setMessage("Internal Server Error");
            errorDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }

    @GetMapping("/{id}")
    private Object loadStaffs(@PathVariable Long id){
        ReponseDTO reponseDTO =  userService.loadStaff(id,Long.parseLong("2"));
        return reponseDTO;
    }

    @PutMapping("/staffs")
    private Object updateAssignmentCustomerById(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO){
        customerService.updateAssignmentCustomerById(assignmentCustomerDTO.getCustomerId(), assignmentCustomerDTO.getStaffs());
        return  new String("ok");
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

            customerService.deleteCustomerByIds(ids);

            reponseDTO.setMessage("Success");
            return ResponseEntity.ok().body(reponseDTO);
        } catch (Exception e) {
            reponseDTO.setMessage("Internal Server Error");
            reponseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reponseDTO);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
       try {
           customerService.addOrUpdateTransaction(transactionDTO);
           ReponseDTO successDTO = new ReponseDTO();
           successDTO.setMessage("Success");
           return ResponseEntity.status(HttpStatus.CREATED).body(successDTO);
       } catch (Exception e) {
           ReponseDTO errorDTO = new ReponseDTO();
           errorDTO.setMessage("Internal Server Error");
           errorDTO.setDetail(Collections.singletonList(e.getMessage()));
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
       }
    }

    @GetMapping("/transactions/{id}")
    public Object loadDetailTransaction(@PathVariable("id") Long id){
        TransactionDTO transactionDTO =  customerService.loadDetailTransaction(id);
        return transactionDTO;
    }

//    @PostMapping("/transactions")
//    public void deleteTransactions(@RequestBody Long id){
//        customerService.deleteTransaction(id);
//    }
}
