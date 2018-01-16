package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {
	
	private static SqlSessionFactory sqlSessionFactory = null;

	//初始化 session 工厂
	static{
		InputStream inputStream  = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @描述 返回 SqlSession
	 * @return SqlSession
	 */
	public static SqlSession getSqlSession(){
		return sqlSessionFactory.openSession();
	}
	
	public static SqlSession getSqlSession(boolean autoCommit){
		return sqlSessionFactory.openSession(autoCommit);
	}
}
