package util;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
/**
 * property를 읽는 클래스
 * @author 쪼임스
 * @since 2018.07.14
 */
public class PropertiesReader {
	/**
	 * property 형식의 문자열을 받아 Properties 객체로 변환
	 * @param str properties형식의 문자열
	 * @return properties
	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	public static Properties readPropString(String str) {
		
		Properties prop = new Properties();
		
		try {
			prop.load(new StringReader(str));
		} catch (IOException e) {
//			LogUtil.printErr("IOExe")
			throw new RuntimeException("fail to load Properties String", e);
		}		
		return prop;
	}
	/**
	 * 해당 경로의 .properties파일을 읽어 Properties 객체 반환
	 * @param filePath properties파일의 경로
	 * @return properties
	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	public static Properties readPropertiesFile(String filePath) {
		Properties prop = new Properties();
		
		try {
			FileReader fr = new FileReader(filePath);
			prop.load(fr);
		} catch (IOException e) {
//			
			throw new RuntimeException("fail to load Properties File", e);
		}		
		return prop;
	}
}
