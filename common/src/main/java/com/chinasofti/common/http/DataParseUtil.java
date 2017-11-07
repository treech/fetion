package com.chinasofti.common.http;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务器返回数据的解析工具；
 * 支持XML,json对象,json数组
 * </p>
 *
 */


public class DataParseUtil {

    private DataParseUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 解析json对象
     *
     * @param string 要解析的json
     * @param clazz  解析类
     */
    public static <T> T parseObject(String string, Class<T> clazz) {
//        return new Gson().fromJson(string, clazz);
        return null;
    }

    /**
     * 解析json数组为ArrayList
     *
     * @param json  要解析的json
     * @param clazz 解析类
     * @return ArrayList
     */
    public static <T> ArrayList<T> parseToArrayList(String json, Class<T> clazz) {
//        Type type = new TypeToken<ArrayList<JsonObject>>() {
//        }.getType();
//        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
//        ArrayList<T> arrayList = new ArrayList<>();
//        for (JsonObject jsonObject : jsonObjects) {
//            arrayList.add(new Gson().fromJson(jsonObject, clazz));
//        }
        return null;
    }

    /**
     * 解析json数组为List
     *
     * @param json  要解析的json
     * @param clazz 解析类
     * @return List
     */
    public static <T> List<T> parseToList(String json, Class<T[]> clazz) {
//        Gson gson = new Gson();
//        T[] array = gson.fromJson(json, clazz);
//        return Arrays.asList(array);
        return null;
    }


    /**
     * 解析Xml格式数据
     *
     * @param json  要解析的json
     * @param clazz 解析类
     */
    public static Object parseXml(String json, Class<?> clazz) {
//        try {
//            if (!TextUtils.isEmpty(json) && clazz != null) {
//                Serializer serializer = new Persister();
//                InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(json.getBytes("UTF-8")), "utf-8");
//                return serializer.read(clazz, is);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

}
