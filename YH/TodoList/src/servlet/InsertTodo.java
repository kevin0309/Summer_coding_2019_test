package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import util.DateUtil;
import util.JSONUtil;
import util.LogUtil;

@WebServlet( name="insertTodo", urlPatterns= {"/addTodo"}, loadOnStartup=1)
public class InsertTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getRequestURI();
		if (command.indexOf(req.getContextPath()) == 0)
			command = command.substring(req.getContextPath().length());
		
		JSONObject obj = new JSONObject();
		String logMsg = "["+command+"] ";
		obj.put("responseDate", DateUtil.getSysdateStr());
		
		try {
			obj.put("resultData", process(req, resp));
			obj.put("statusMsg", "OK");
			logMsg += "Status : OK";
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("resultData", "");
			obj.put("statusMsg", "Server Logic error occured. ("+e.getLocalizedMessage()+")");
			logMsg += "Status : Server Logic error occured. ("+e.getMessage()+")";
		}
		resp.setContentType("application/json");	//MIME type 마임타입이라 한다. 컨텐츠타입에 담기는 건 마임타입이다. 마임타입을 보고 브라우져에서 뭘 할지를 정한다.
		resp.getWriter().println(JSONUtil.toString_obj(obj));
		resp.getWriter().flush();
		resp.getWriter().close();
		LogUtil.printLog(req.getRemoteAddr(), logMsg);
	}
	
	private JSONAware process(HttpServletRequest req, HttpServletResponse resp) {
		
		return null;
	}
}
