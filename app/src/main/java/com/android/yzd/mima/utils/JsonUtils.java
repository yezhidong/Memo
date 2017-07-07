package com.android.yzd.mima.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

/**
 * <p>Title:        JsonUtils
 * <p>Description:  json解析帮助类
 * <p>@author:      yzd
 * <p>Create Time:  2017/6/1 下午8:45
 */
public class JsonUtils {

    /**
     * 将json解析成对象
     * @param json json传
     * @param classParam 对象的类
     */
    public static Object fromJson(String json, Class<?> classParam){
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return JSON.parseObject(json, classParam, Feature.InitStringFieldAsEmpty);
    }

}
