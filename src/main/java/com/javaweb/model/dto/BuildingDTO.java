package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO {
    private Long id;

    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotBlank(message = "Street can not be blank")
    private String street;

    @NotBlank(message = "Ward can not be blank")
    private String ward;

    @NotBlank(message = "District can not be blank")
    private String district;

    @NotNull(message = "numberOfBasement can not be null")
    @Digits(integer = 10, fraction = 0, message = "numberOfBasement phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "numberOfBasement phải lớn hơn hoặc bằng 1")
    private Long numberOfBasement;

    @NotNull(message = "floorArea can not be blank")
    @Digits(integer = 10, fraction = 0, message = "floorArea phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "floorArea phải lớn hơn hoặc bằng 1")
    private Long floorArea;

    @NotNull(message = "level can not be blank")
    @Digits(integer = 10, fraction = 0, message = "level phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "level phải lớn hơn hoặc bằng 1")
    private Long level;

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

    @NotBlank(message = "direction can not be blank")
    private String direction;

    private String note;

    @Pattern(regexp = "(\\d{1,9})(,\\d{1,9})*", message = "floorArea phải có dạng từ 1 đến 9 chữ số, ngăn cách bởi dấu phẩy, ví dụ: 1,22,333")
    private String rentArea;

    @NotBlank(message = "note can not be blank")
    private String managerName;

    @NotBlank(message = "note can not be blank")
    private String managerPhone;

    @NotNull(message = "rentPrice can not be blank")
    @Digits(integer = 10, fraction = 0, message = "rentPrice phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "rentPrice phải lớn hơn hoặc bằng 1")
    private Long rentPrice;


    private Long serviceFee;


    private Double brokerageFee;

    private String image;

    private String imageBase64;

    private String imageName;
}
