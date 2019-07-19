package util;

/**
 * 로그의 형식통일 위한 클래스
 * @author 쪼임스
 * @since 2018.7.14
 */
public class LogUtil {
	/**
	 * 로그 프린트하는 메서드
	 * @param content 로그 내용
	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	public static void printLog(String content) {
		System.out.println(makeLogStr(null, content, false));
	}
	
	/**
	 * 로그 프린트하는 메서드
	 * @param ip 요청 ip
	 * @param content 로그 내용
	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	public static void printLog(String ip, String content) {
		System.out.println(makeLogStr(ip, content, false));
	}
	
	/**
	 * 로그 프린트하는 메서드
	 * @param content 로그 내용
 	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	public static void printErrLog(String content) {
		System.err.println(makeLogStr(null, content, true));
	}
	
	/**
	 * 로그 프린트하는 메서드
	 * @param ip 요청 ip
	 * @param content 로그 내용
	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	public static void printErrLog(String ip, String content) {
		System.err.println(makeLogStr(ip, content, true));
	}
	/**
	 * 로그에 찍힐 문자열을 생성하는 메서드
	 * @param ip 요청 ip
	 * @param content 로그 내용
	 * @return 로그 문자열 리턴
	 * @author 쪼임스
	 * @since 2018.07.14
	 */
	private static String makeLogStr(String ip, String content, boolean isErr) {
		StackTraceElement[] trace = new Throwable().getStackTrace();
		
		if(ip == null)
			ip = "";
		else
			ip = "[" + ip + "]";
		
		String[] temp = trace[2].getClassName().split("\\.");
		String className = "[" + temp[temp.length-1] + "]";
		String res = "["+DateUtil.getSysdateStr()+"][TodoList]" + className + ip + " - " + content;
		return res;
	}
}
