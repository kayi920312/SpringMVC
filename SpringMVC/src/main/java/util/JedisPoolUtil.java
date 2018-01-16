package util;

import java.io.IOException;
import java.util.Properties;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedisPool 工具类，单例模式
 * @author kaiying.chen
 */
public class JedisPoolUtil {
	
	private JedisPoolUtil(){}

	/* ============================= 双重锁定 单例法 ================================ */
	private static volatile JedisPool jedisPool = null;
	
	/**
	 * 获取 jedisPool
	 * @return JedisPool
	 * @throws IOException
	 */	
	public static JedisPool getJedisPool() throws IOException{
		if(jedisPool==null){
			synchronized (JedisPoolUtil.class) {
				if(jedisPool==null){
					
					Properties properties = new Properties();
					properties.load(JedisPoolUtil.class.getClassLoader().getResourceAsStream("conf/properties/conf.properties"));
					
				    JedisPoolConfig poolConfig = new JedisPoolConfig();
		            poolConfig.setMaxTotal(1000);
		            poolConfig.setMaxIdle(30);
		            poolConfig.setMaxWaitMillis(1000*10);
		            poolConfig.setTestOnBorrow(true);
		            
					jedisPool = new JedisPool(poolConfig, properties.getProperty("jedisIp"));
					
					return jedisPool;
				}else{
					return jedisPool;
				}
			}
		}else{
			return jedisPool;
		}
	}
	/* ============================= 双重锁定 单例法 ================================ */
	
	/* ============================= 静态内部类 单例法 ================================ */
	/*private static class InnerInstance {
		private static volatile JedisPool jedisPool;
		private InnerInstance(){
			
			Properties properties = new Properties();
			try {
				properties.load(JedisPoolUtil.class.getClassLoader().getResourceAsStream("conf/properties/conf.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		    JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(1000);
            poolConfig.setMaxIdle(32);
            poolConfig.setMaxWaitMillis(1000*10);
            poolConfig.setTestOnBorrow(true);
            
			jedisPool = new JedisPool(poolConfig, properties.getProperty("jedisIp"));
		}
	}

	public static JedisPool getJedisPool() {
		return InnerInstance.jedisPool;
	}*/
	/* ============================= 静态内部类 单例法 ================================ */
}
