package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 날짜 관련 유틸을 제공하는 클래스
 * @author 쪼임스
 * @since 2018.07.14
 */
public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 현재 시간을 받아오는 메서드
	 * @return str
	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	public static String getSysdateStr() {
		return sdf.format(new Date());
	}
	/**
	 * 날짜를 받아서 String 형식으로 변환해주는 메서드
	 * @param str
	 * @return str
	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	public static String toString(Date date) {
		return sdf.format(date);
	}
	
	/**
	 * 우리가 리턴한 형식으로 된 문자열을 받아서, Date로 바꾸는 메서드
	 * @param str
	 * @return Date
	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	public static Date toDate(String str) {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
//			
			throw new RuntimeException("DateUtil parse error", e);
		}
	}
}
