package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum DistrictCode {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_7("Quận 7"),
    QUAN_8("Quận 8");


    private final String districtName;
    DistrictCode(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public static DistrictCode getdistrictCodeByValue(String value) {
        for (DistrictCode districtCode : DistrictCode.values()) {
            if (districtCode.toString().equals(value)) {
                return districtCode;
            }
        }
        return null;
    }

    public static Map<String, String> type(){
        Map<String, String> map = new LinkedHashMap<>();
        for(DistrictCode t : DistrictCode.values()){
            map.put(t.toString(), t.getDistrictName());
        }
        return map;
    }
}
