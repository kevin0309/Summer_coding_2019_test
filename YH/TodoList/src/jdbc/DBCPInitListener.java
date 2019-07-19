package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.PoolableConnection;
import org.apache.tomcat.dbcp.dbcp2.PoolableConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.PoolingDriver;
import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPool;
import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPoolConfig;

import util.LogUtil;
import util.PropertiesReader;

/**
 * DB Connection pool을 구현하는 클래스
 * @author 유현
 * @since 2018.07.22
 */
@WebListener
public class DBCPInitListener implements ServletContextListener{
	private static String poolName;
	private GenericObjectPool<PoolableConnection> connectionPool;
	private PoolingDriver driver;
	
	/**
	 * 서버 시작 시 DBCP를 만듬
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Properties prop = PropertiesReader.readPropString(sce.getServletContext().getInitParameter("DBCPInitConfig"));
		loadDrivers(prop);
		initConnectionPool(prop);
		LogUtil.printLog("DBCP initiated successfully.");
	}
	
	/**
	 * DBCP 구성에 필요한 class를 로드시키는 메서드
	 * @param prop
	 */
	private void loadDrivers(Properties prop) {
		String JDBCDriverClass = prop.getProperty("JDBCDriver");
		
		try {
			Class.forName(JDBCDriverClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("fail to load JDBC Drier", e);
		}	
		
		String PoolingDriverClass = prop.getProperty("poolingDriver");
		
		try {
			Class.forName(PoolingDriverClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("fail to load poolingDriver", e);
		}
	}
	
	/**
	 * 설정값으로 DBCP를 만드는 메서드 상당히 정형화된 코드임
	 * @param prop
	 */
	private void initConnectionPool(Properties prop) {
		try {
			String serverAddr = prop.getProperty("DBServerAddress");
			String serverPort = prop.getProperty("DBServerPort");
			String JDBCUrl = prop.getProperty("JDBCUrl");
			JDBCUrl = JDBCUrl.replace("{@address}", serverAddr);
			JDBCUrl = JDBCUrl.replace("{@port}", serverPort);
			JDBCUrl += "?autoReconnect=true&useSSL=false&serverTimezone=UTC";
			String user = prop.getProperty("dbUser");
			String pw = prop.getProperty("dbPass");
			//pw = encodePassword(pw);
			String validationQuery = prop.getProperty("validationQuery");
			String poolName = prop.getProperty("poolName");
			int minIdle = getIntProperty(prop, "minIdle", 10);
			int maxTotal = getIntProperty(prop, "maxTotal", 50);
			
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(JDBCUrl, user, pw);
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			if (validationQuery != null && validationQuery.isEmpty())
				poolableConnFactory.setValidationQuery(validationQuery);
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(minIdle);
			poolConfig.setMaxIdle(maxTotal);
			
			this.connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			this.driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool(poolName, connectionPool);
			
			DBCPInitListener.poolName = poolName;

			poolableConnFactory.validateConnection(poolableConnFactory.getPool().borrowObject());
		} catch (Exception e) {
			throw new RuntimeException("fail to init Connection Pool", e);
		}
	}
	
	/**
	 * 서버가 꺼질 때 DBCP 자원을 반납하는 메서드
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			this.driver.closePool(DBCPInitListener.poolName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * properties에서 정수값을 가져오기위해 구현한 기능
	 * @param prop
	 * @param key
	 * @param def
	 * @return
	 */
	private int getIntProperty(Properties prop, String key, int def) {
		String value = prop.getProperty(key);
		if (value == null)
			return def;
		return Integer.parseInt(value);
	}
	
	/*private String encodePassword(String pw) {
		AES256Cipher cipher = AES256Cipher.getInstance("ProjectVoca23456");
		try {
			return cipher.AES_Encode(pw);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}*/
	
	/**
	 * poolName 확인을 위한 메서드
	 * @return
	 */
	public static String getPoolName() {
		return poolName;
	}
}
