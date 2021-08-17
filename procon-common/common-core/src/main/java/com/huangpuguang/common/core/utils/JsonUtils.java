package com.huangpuguang.common.core.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json工具类
 *
 * @author procon
 * @date 2020-08-01 14:26
 */
public class JsonUtils {
    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static Logger log = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 把对象转换为json数据
     *
     * @param  obj 对象
     * @return  String
     * @author procon
     */
    public static String objectToJson(Object obj) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Object转换成Map
     *
     * @param obj 对象
     * @return Map
     */
    public static Map<String, Object> objectToMap(Object obj) {

        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String json = gson.toJson(obj);
            return jsonToMap(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 把json字符串转化为对象
     *
     * @param jsonString 把json字符串
     * @param clazz  转换成的对象
     * @return Object
     */
    public static Object jsonToObject(String jsonString, Class<?> clazz) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Object obj = null;
        try {
            obj = gson.fromJson(jsonString, clazz);
        } catch (JsonSyntaxException e) {

            e.printStackTrace();
        }
        return obj;
    }

    /**
     * json转arrayList
     *
     * @param jsonArray json数组字符串
     * @return ArrayList
     */
    public static ArrayList<?> jsonArrayToArrayList(String jsonArray) {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        ArrayList<?> list = null;
        try {
            Type listType = new TypeToken<ArrayList<?>>() {
            }.getType();

            list = gson.fromJson(jsonArray, listType);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * JSON 转 ArrayList
     *
     * @author xzx19950624@qq.com
     * @date 2018年10月27日下午4:43:25
     */
    public static ArrayList<?> jsonArrayToArrayList(String jsonArray, Class<?> clazz) {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        ArrayList<?> list = null;
        try {

            list = (ArrayList<?>) gson.fromJson(jsonArray, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 把json转换为map类型的数据
     */
    public static Map<String, Object> jsonToMap(String json) {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        Map<String, Object> map = null;
        try {
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();

            map = gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将Json转换成Map<String, ?>
     */
    public static Map<String, ?> jsonToMap(String json, Class<?> clazz) {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        Map<String, ?> map = null;
        try {
            Type type = new TypeToken<Map<String, ?>>() {
            }.getType();

            map = gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将map转换成pojo
     *
     */
    public static <T> T mapToPojo(Map<String, Object> map, Class<T> beanType) {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();

        JsonElement jsonElement = gson.toJsonTree(map);

        return gson.fromJson(jsonElement, beanType);
    }

    /**
     * 将json结果集转化为对象
     *
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将任意pojo转化成map
     */
    public static <T> Map<String, Object> pojoToMap(T t) {
        Map<String, Object> result = new HashMap<>();
        Method[] methods = t.getClass().getMethods();
        try {
            for (Method method : methods) {
                Class<?>[] paramClass = method.getParameterTypes();
                // 如果方法带参数，则跳过
                if (paramClass.length > 0) {
                    continue;
                }
                String methodName = method.getName();
                if (methodName.startsWith("get")) {
                    Object value = method.invoke(t);
                    result.put(methodName, value);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }

}
