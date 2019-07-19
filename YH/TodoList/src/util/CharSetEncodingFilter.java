package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 서버가 실행될 때 Char인코딩을 설정값으로 바꿔주는 클래스
 * @author 유현
 * @since 18.07.22
 */
@WebFilter (
	urlPatterns = "/*",
	initParams = @WebInitParam(name = "default", value = "UTF-8")
)
public class CharSetEncodingFilter implements Filter{

	/**
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
	}
	/**
	 * 서버가 시작할 때 초기화를 위한 메서드
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}	
}
