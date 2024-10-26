package com.javaweb.utils;

public class ObjectUtils {
    public static boolean check(Object data) {
        if(data != null && !data.equals(""))return true;
        else return false;
    }
}
