package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO {
    private Long id;

    @NotBlank(message = "Name can not be blank")
    private String name;
    private String street;
    private String ward;
    private String district;
    private Long numberOfBasement;
    private Long floorArea;
    private String level;

    @NotNull(message = "TypeCode can not be null")
    private List<String> typeCode;
    private Long overtimeFee;
    private Long electricityFee;
    private Long deposit;
    private Long payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private Long carFee;
    private String structure;
    private String direction;
    private String note;
    private String rentArea;
    private String managerName;
    private String managerPhone;
    @Min(value = 0, message = "Rent Price must be >= 0")
    private Long rentPrice;
    private Long serviceFee;
    private Double brokerageFee;
    private String image;
    private String imageBase64;
    private String imageName;
}
