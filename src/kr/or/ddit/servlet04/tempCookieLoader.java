package kr.or.ddit.servlet04;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class tempCookieLoader
 */
@WebServlet("/tempCookieLoader")
public class tempCookieLoader extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "WEB-INF/views/tempJson.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	
	}

}
