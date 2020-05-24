package cn.prosayj.blog.core.util;

import cn.prosayj.blog.core.common.constants.enums.IsDeleted;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


/**
 * BeanUtil
 *
 * @author yangjian@bubi.cn
 * @date 2020-05-10 下午 09:21
 * @since 1.0.0
 */
@Component
public class BeanUtil {
    public static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * json字符串转list对象数组
     *
     * @param jsonString
     * @param objectClass
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonString2ObjectList(String jsonString, Class<T> objectClass) {
        return JSON.parseArray(jsonString, objectClass);
    }

    /**
     * Json字符串转化成对象
     *
     * @param jsonString json格式的字符串
     * @param clazz      需要被转换的对象
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T jsonString2Obj(String jsonString, Class<T> clazz) {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            //Json字符串转化成对象出错
        }
        return null;
    }

    public static <T> T initBeanProperties(T bean) {
        BeanUtils.copyProperties(new InitBeanProperties(), bean);
        return bean;
    }

    static class InitBeanProperties {
        private Date createDate;
        private Date updateDate;
        private Byte isDelete;

        public InitBeanProperties() {
            Date now = new Date();
            this.isDelete = IsDeleted.UN_DELETED.getCode();
            this.createDate = now;
            this.updateDate = now;
        }

        public Date getCreateDate() {
            return createDate;
        }

        public Date getUpdateDate() {
            return updateDate;
        }

        public Byte getIsDelete() {
            return isDelete;
        }
    }

    /**
     * json格式的字符串转map
     *
     * @param string
     * @return
     */
    public static Map<String, String> jsonString2Map(String string) {
        return JSONObject.toJavaObject((JSON) JSONObject.parse(string), Map.class);
    }

    public static void main(String[] args) {
        String jsonString = "{\"age\":\"23\",\"name\":\"张三\"}";
//        System.out.println(jsonString2Obj(jsonString, Student.class));
        System.out.println(jsonString2Map(jsonString));

    }

    /**
     * @description 对象转json字符串
     * @author yangjian
     * @Date 10:57 2018/9/19
     * @since 1.0.0
     */
    public static String object2StringFastJson(Object object) {
        /*
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String result = gson.toJson(object);
        */
        return JSONObject.toJSONString(object);
    }

    /**
     * javaBean 转化成json字符串
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String object2StringJackSon(Object obj) {
        if (obj instanceof Integer || obj instanceof Long || obj instanceof Float ||
                obj instanceof Double || obj instanceof Boolean || obj instanceof String) {
            return String.valueOf(obj);
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            //转化成json字符串
        }
        return null;
    }

    /**
     * @description 将实体转换成Map
     * @author yangjian
     * @Date 10:55 2018/9/19
     * @since 1.0.0
     */
    public static Map<String, String> objectConvertToMap(Object object) {
        Map<String, String> result = new HashMap<>();
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                Object eleContent;
                try {
                    eleContent = field.get(object);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
                if (eleContent == null) {
                    result.put(field.getName(), null);
                    continue;
                }
                result.put(field.getName(), eleContent.toString());
            }
        }
        return result;
    }


    /**
     * @description 拷贝对象属性
     * @author yangjian
     * @Date 10:53 2018/9/19
     * @since 1.0.0
     */
    public static void copyProperties(Object source, Object target) {
        try {
            org.springframework.beans.BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * @param jsonString
     * @param object
     * @throws
     * @description jsonSting to object
     * @author yangjian
     * @Date 11:26 2018/7/27
     * @since 1.0.0
     */
    public static <T> T jsonString2Bean(String jsonString, Class<T> object) {
        return JSONObject.parseObject(jsonString, object);
        /*
        try {
            return objectMapper.readValue(jsonString, object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        */
    }

    /**
     * 拷贝对象数组
     *
     * @param sourecList
     * @param targeClass
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> toBeanList(List<S> sourecList, Class<T> targeClass) {
        List<T> result = new ArrayList<>(sourecList.size());
        sourecList.forEach(source -> {
            //Class<S> sourceClass = (Class<S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            result.add(jsonString2Bean(object2StringFastJson(source), targeClass));
            return;
        });
        return result;
    }

    //初始化json字符串
    public static final String bankJsonString = "[\n" +
            "{\n" +
            "\"id\": 2,\n" +
            "\"bankCode\": \"PBC\",\n" +
            "\"bankName\": \"中国人民银行\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 5,\n" +
            "\"bankCode\": \"ICBC\",\n" +
            "\"bankName\": \"中国工商银行\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 6,\n" +
            "\"bankCode\": \"ABC\",\n" +
            "\"bankName\": \"中国农业银行\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 7,\n" +
            "\"bankCode\": \"BOC\",\n" +
            "\"bankName\": \"中国银行\"\n" +
            "},\n" +
            "{\n" +
            "\"id\": 8,\n" +
            "\"bankCode\": \"CDB\",\n" +
            "\"bankName\": \"国家开发银行\"\n" +
            "}\n" +
            "]";
}
