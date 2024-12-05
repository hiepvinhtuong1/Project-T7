package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.ReponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> findAll(CustomerDTO request, Pageable pageable);
    CustomerDTO findById(Long id);
    int countTotalItems(CustomerDTO request);
    CustomerEntity createOrUpdateCustomer(CustomerDTO customerDTO) throws Exception;
    void updateAssignmentCustomerById(Long id, List<Long> staffId) ;
    void deleteCustomerByIds(List<Long> ids);
    boolean checkUserOfCustomer(Long userId, Long customerId);
    void addOrUpdateTransaction(TransactionDTO transactionDTO);
    TransactionDTO loadDetailTransaction(Long id);
}
