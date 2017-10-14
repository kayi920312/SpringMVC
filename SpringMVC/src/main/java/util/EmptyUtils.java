package util;

/**
 * 判断空对象工具类
 * @author kayi9
 *
 */
public class EmptyUtils {
	
	/**
	 * 判断单个对象
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj){
		if(obj==null||"".equals(obj)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断多个对象
	 * @param obj
	 * @return
	 */
	public static boolean atLeastOneIsEmpty(String...objs){
		for (String obj : objs) {
			if(obj==null||"".equals(obj)){
				return true;
			}
		}
		return false;
	}
}
