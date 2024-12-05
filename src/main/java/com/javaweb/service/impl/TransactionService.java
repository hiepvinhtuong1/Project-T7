package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionConverter transactionConverter;

    @Autowired
    CustomerRepository   customerRepository;
    @Override
    public List<TransactionDTO> getListTransaction(String code, Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        List<TransactionEntity> transactionEntityList = transactionRepository.findByCustomerIdAndAndCode(customerEntity, code);
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntityList) {
            TransactionDTO transactionDTO = transactionConverter.converterToDto(transactionEntity);
            transactionDTOList.add(transactionDTO);
        }
        return transactionDTOList;
    }
}
