package com.my.blog.website.utils;

import com.google.gson.Gson;

/**
 * ************************************
 *
 * @ClassName: GsonUtils
 * @Auther: dangjinhu
 * @Date: 2018/3/13
 * @Description: json转换工具
 * @Copyright: All rights reserver.
 * ************************************
 */
public class GsonUtils {

    private static final Gson gson = new Gson();

    public static String toJsonString(Object object){
      return object==null?null:gson.toJson(object);
    }
}
