package util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

public class MyBatisCahe implements Cache{

	private ReadWriteLock lock = new ReentrantReadWriteLock();
    private ConcurrentHashMap<Object, Object> cache = new ConcurrentHashMap<Object, Object>();
//    private HashMap<Object, Object> cache = new HashMap<Object, Object>();
    
    private String id;
    private String cacheFile;
    
    public String getCacheFile() {
		return cacheFile;
	}

	public void setCacheFile(String cacheFile) {
		this.cacheFile = cacheFile;
	}

	public MyBatisCahe(String id){
    	System.out.println("id=: "+id);
    	this.id = id;
    }
    
	@Override
	public void clear() {
		System.out.println("clear");
		cache.clear();
	}

	@Override
	public String getId() {
		System.out.println("getId: "+id);
		return id;
	}

	@Override
	public Object getObject(Object arg0) {
		System.out.println("getObject");
		return cache.get(arg0);
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		System.out.println("getReadWriteLock");
		return lock;
	}

	@Override
	public int getSize() {
		System.out.println("getSize");
		return cache.size();
	}

	@Override
	public void putObject(Object arg0, Object arg1) {
		System.out.println("putObject");
		cache.put(arg0, arg1);
	}

	@Override
	public Object removeObject(Object arg0) {
		System.out.println("removeObject");
		return cache.remove(arg0);
	}

}
