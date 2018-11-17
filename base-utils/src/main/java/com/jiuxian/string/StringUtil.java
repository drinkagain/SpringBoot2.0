package com.jiuxian.string;

public class StringUtil {

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        return null == str || str.trim().length() == 0 || str.trim().equalsIgnoreCase("null");
    }

}
