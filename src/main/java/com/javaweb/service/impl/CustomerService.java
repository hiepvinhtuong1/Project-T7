package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ReponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private  CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<CustomerDTO> findAll(CustomerDTO request, Pageable pageable) {
        List<CustomerEntity> customerEntities = customerRepository.findAll(request,pageable);

        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for (CustomerEntity customerEntity : customerEntities) {
            CustomerDTO customerDTO = customerConverter.converterToDto(customerEntity);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        CustomerDTO customerDTO = customerConverter.converterToDto(customerEntity);
        return customerDTO;
    }

    @Override
    public int countTotalItems(CustomerDTO request) {
        return customerRepository.countTotalItems(request);
    }

    @Override
    public CustomerEntity createOrUpdateCustomer(CustomerDTO customerDTO) throws Exception {
        CustomerEntity customerEntity = customerConverter.converterToEntity(customerDTO);

        if (StringUtils.check(customerDTO.getPhone()) &&customerRepository.existsByPhone(customerDTO.getPhone())) {
            throw new MyException("Số điện thoại đã tồn tại");
        }

        if (StringUtils.check(customerDTO.getEmail()) && customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw  new MyException("Email đã tồn tại");
        }

        customerEntity.setIs_active(1);
        return customerRepository.save(customerEntity);
    }

    @Override
    public void updateAssignmentCustomerById(Long id, List<Long> staffIds){
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        if (customerEntity != null) {
            List<UserEntity> userEntities = userRepository.findByIdIn(staffIds);
            customerEntity.setUsers(userEntities);
            customerRepository.save(customerEntity);
        }
    }

    @Override
    public void deleteCustomerByIds(List<Long> ids) {
        for (Long id : ids) {
            CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
            if (customerEntity != null) {
                customerEntity.setIs_active(0);

                List<UserEntity> userEntities = customerEntity.getUsers();

                for (UserEntity userEntity : userEntities) {
                    userEntity.getCustomers().remove(customerEntity);
                }

                customerEntity.getUsers().clear();

                customerRepository.save(customerEntity);
            }
        }
    }

    @Override
    public boolean checkUserOfCustomer(Long userId, Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElse(null);
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (customerEntity.getUsers().contains(userEntity)) {
            return true;
        }
        return false;
    }

    @Override
    public void     addOrUpdateTransaction(TransactionDTO transactionDTO) {
        if (transactionDTO.getId() != null) {
            TransactionEntity transactionEntity = transactionRepository.findById(transactionDTO.getId()).orElse(null);
            if (transactionEntity != null){
                transactionEntity.setNote(transactionDTO.getNote());
                transactionEntity.setModifiedDate(transactionDTO.getModifiedDate());
                transactionEntity.setModifiedBy(SecurityUtils.getPrincipal().getId().toString());
                transactionRepository.save(transactionEntity);
            }
        } else {
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setNote(transactionDTO.getNote());
            transactionEntity.setCode(transactionDTO.getCode());
            CustomerEntity customerEntity = customerRepository.findById(transactionDTO.getCustomerId()).orElse(null);
            transactionEntity.setCustomerId(customerEntity);
            transactionEntity.setCreatedBy(SecurityUtils.getPrincipal().getId().toString());
            transactionEntity.setCreatedDate(new Date());
            UserEntity userEntity = userRepository.findById(SecurityUtils.getPrincipal().getId()).orElse(null);
            transactionEntity.setStaffId(userEntity);
            transactionRepository.save(transactionEntity);
        }
    }

    @Override
    public TransactionDTO loadDetailTransaction(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).orElse(null);
        TransactionDTO transactionDTO = transactionConverter.converterToDto(transactionEntity);
        return transactionDTO;
    }
}
