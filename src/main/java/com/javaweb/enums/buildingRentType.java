package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum buildingRentType {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội Thất");

    private final String name;

    buildingRentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Map<String, String> type(){
        Map<String, String> map = new LinkedHashMap<>();
        for(buildingRentType t : buildingRentType.values()){
            map.put(t.toString(), t.getName());
        }
        return map;
    }
}
