package util;

import com.google.gson.Gson;

/**
 * 封装gson工具
 * @author kayi9
 *
 */
public class GsonUtil {
	
	private final static Gson gson = new Gson();
	
	/**
	 * Object to JSON
	 * @param Object obj
	 * @return
	 */
	public final static String toJson(Object obj){
		return gson.toJson(obj);
	}
	
	/**
	 * JSON to Object
	 * @param String json,Class<T> classOfT
	 * @return
	 */
	public final static <T> T toBean(String json, Class<T> classOfT){
        return gson.fromJson(json, classOfT);
    }
	
}
