package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.javaweb.enums.Status.getStatusByValue;

@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO converterToDto(CustomerEntity customerEntity){
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);

        return customerDTO;
    }

    public CustomerEntity converterToEntity(CustomerDTO customerDTO){
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        return customerEntity;
    }
}
