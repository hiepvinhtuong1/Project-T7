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

    @NotNull(message = "overtimeFee can not be blank")
    @Digits(integer = 10, fraction = 0, message = "overtimeFee phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "overtimeFee phải lớn hơn hoặc bằng 1")
    private Long overtimeFee;

    @NotNull(message = "electricityFee can not be blank")
    @Digits(integer = 10, fraction = 0, message = "electricityFee phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "electricityFee phải lớn hơn hoặc bằng 1")
    private Long electricityFee;

    @NotNull(message = "deposit can not be blank")
    @Digits(integer = 10, fraction = 0, message = "deposit phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "deposit phải lớn hơn hoặc bằng 1")
    private Long deposit;

    @NotNull(message = "payment can not be blank")
    @Digits(integer = 10, fraction = 0, message = "payment phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "payment phải lớn hơn hoặc bằng 1")
    private Long payment;

    @NotBlank(message = "rentTime can not be blank")
    private String rentTime;

    @NotBlank(message = "decorationTime can not be blank")
    private String decorationTime;

    @NotBlank(message = "rentPriceDescription can not be blank")
    private String rentPriceDescription;

    @NotNull(message = "carFee can not be blank")
    @Digits(integer = 10, fraction = 0, message = "carFee phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "carFee phải lớn hơn hoặc bằng 1")
    private Long carFee;

    @NotBlank(message = "structure can not be blank")
    private String structure;

    @NotBlank(message = "direction can not be blank")
    private String direction;

    @NotBlank(message = "note can not be blank")
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

    @NotNull(message = "serviceFee can not be blank")
    @Digits(integer = 10, fraction = 0, message = "serviceFee phải là số nguyên và không vượt quá 10 chữ số")
    @Min(value = 1, message = "serviceFee phải lớn hơn hoặc bằng 1")
    private Long serviceFee;

    @Min(value = 1, message = "serviceFee phải lớn hơn hoặc bằng 1")
    @Digits(integer = 10, fraction = 2, message = "Số không được có quá 10 chữ số phần nguyên và 2 chữ số phần thập phân")
    private Double brokerageFee;

    @NotBlank(message = "image can not be blank")
    private String image;

    @NotBlank(message = "imageBase64 can not be blank")
    private String imageBase64;

    @NotBlank(message = "imageName can not be blank")
    private String imageName;
}
