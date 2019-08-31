package kr.or.ddit;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class CalculateServlet extends HttpServlet{
	
	public enum OperatorType{
		PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");
		
		String sign;
		
		public String getSign() {
			return sign;
		}
		
		private OperatorType(String sign) {
			this.sign = sign;
		}

		public static String operation(String sign) {
			String result = null;
			for(OperatorType temp : values()) {
				if(sign.toUpperCase().contains(temp.name())) {
					result = temp.getSign();
				}
			}
			return result;
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/plain;charset=UTF-8");
		String uri = "/01/calculateForm.jsp";
		String name = req.getParameter("name");
		String op = OperatorType.operation(req.getParameter("operator"));
		String leftTemp = req.getParameter("leftOp");
		String rightTemp = req.getParameter("rightOp");
		String frmt = "%s 님이 수행한 연산결과 => %.2f %s %.2f = %.2f";
		
		if(leftTemp=="" || rightTemp=="") {
			frmt = "숫자입력바랍니다";
		}else {
			double leftOp = Double.parseDouble(leftTemp);
			double rightOp = Double.parseDouble(rightTemp);
			double result =0;
			switch(op) {
				case "+" :
					result = leftOp+rightOp;
					break;
				case "-" :
					result = leftOp-rightOp;
					break;
				case "*" :
					result = leftOp*rightOp;
					break;
				case "/" :
					result = leftOp/rightOp;
					break;
			}
			frmt = String.format(frmt, name, leftOp, op, rightOp,result);
		}
//		try(
//		PrintWriter out = resp.getWriter();
//		){
//			out.println(frmt);
//		}
		req.setAttribute("result", frmt);
		RequestDispatcher dispatcher = req.getRequestDispatcher(uri);
		dispatcher.forward(req, resp);
	}
	
}
