package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO converterToDto(TransactionEntity transactionEntity){
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        return transactionDTO;
    }
}
