package com.wherepeople.spring.mvc.util;

import com.google.gson.Gson;

/**
 * Created by yuriy on 20.03.15.
 */
public class WebServiceUtil {
    public static final Gson GSON = new Gson();

    public static boolean isEmptyOrNull(String value){
        return value == null || value.isEmpty();
    }
}
