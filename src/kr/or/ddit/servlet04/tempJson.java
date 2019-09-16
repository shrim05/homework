package kr.or.ddit.servlet04;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class tempJson
 */
@WebServlet("/tempJson")
public class tempJson extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonData = request.getParameter("json");
		String tem = URLDecoder.decode(jsonData,"UTF-8");
		String value = URLEncoder.encode(jsonData,"UTF-8");
		Cookie cookie = new Cookie("imgCookie",value);
		cookie.setMaxAge(60*60*24*2);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
