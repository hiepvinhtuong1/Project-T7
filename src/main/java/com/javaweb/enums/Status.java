package com.javaweb.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum Status {
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý"),
    CHUA_XU_LY("Chưa xử lý");

    private final String statusName;
    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public static Status getStatusByValue(String value) {
        for (Status status : Status.values()) {
            if (status.toString().equals(value)) {
                return status;
            }
        }
        return null;
    }


    public static Map<String, String> type(){
        Map<String, String> map = new LinkedHashMap<>();
        for (Status s : Status.values()) {
            map.put(s.toString(), s.getStatusName());
        }
        return map;
    }
}
