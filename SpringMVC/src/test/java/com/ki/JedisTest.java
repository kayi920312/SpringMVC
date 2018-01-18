package com.ki;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;

public class JedisTest {

	public static void main(String[] args) {
		
		/*Jedis jedis = new Jedis("10.4.91.146", 6379);
		System.out.println(jedis.ping());
		jedis.close();*/
		
		/*JedisPool jedisPool = null;
		Jedis jedis = null;
		try{
			jedisPool = JedisPoolUtil.getJedisPool();
			jedis = jedisPool.getResource();
			System.out.println(jedis.clientGetname());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jedis.close();
		}*/
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("/conf/spring/applicationContext.xml");
//		StringRedisTemplate redisTemplate = (StringRedisTemplate) ac.getBean("redisTemplate");
		
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) ac.getBean("redisTemplate");
		
		HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
		HashMapper<Person, String, String> mapper = new BeanUtilsHashMapper(Person.class);
		hashOperations.putAll("person", mapper.toHash(new Person("PANG", 26)));
		
		/*HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();
		hashOperations.putAll("person", mapper.toHash(new Person("PANG", 26)));
		Person person = (Person) mapper.fromHash(hashOperations.entries("person"));
		System.out.println(person.toString());*/
		
		/*HashMapper<Object, String, Object> mapper = new Jackson2HashMapper(true);
		mapper.toHash(new Person("PANG", 26));*/
		
		
		/*System.out.println(redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}}));*/
		
		
//		redisTemplate.setKeySerializer(new org.springframework.data.redis.serializer.StringRedisSerializer());
//		redisTemplate.setValueSerializer(new org.springframework.data.redis.serializer.OxmSerializer());
//		redisTemplate.setHashKeySerializer(new org.springframework.data.redis.serializer.StringRedisSerializer());
//		redisTemplate.setHashValueSerializer(new org.springframework.data.redis.serializer.StringRedisSerializer());
//		redisTemplate.opsForHash().put("user", "name", "LONG");
		
//		redisTemplate.opsForList().leftPush("PANG", new Person("PANG", 26));
		return;
	}
}

class Person implements Serializable{
	
	private static final long serialVersionUID = 3121726002493212524L;
	
	private String name;
	private int age;
	
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "name: "+name+", age: "+age;
	}
}
