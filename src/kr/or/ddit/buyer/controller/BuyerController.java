package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.enums.ServiceResult;

/**
 * Servlet implementation class BuyerController
 */
@WebServlet("/BuyerController")
public class BuyerController extends HttpServlet {
	IBuyerService service;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = BuyerServiceImpl.getInstance();
		
		String accept = request.getHeader("Accept");
		if(accept.contains("json")) {
			String command = request.getParameter("command");
			switch(command) {
				
			}
		}else {
			BuyerVO bv = new BuyerVO();
			List<BuyerVO> lbv = service.retrieveBuyerList();
			bv.setBuyer_id("122");
			bv.setBuyer_name("12");
			bv.setBuyer_lgu("P302");
			bv.setBuyer_bank("12");
			bv.setBuyer_bankno("12");
			bv.setBuyer_bankname("12");
			bv.setBuyer_zip("12");
			bv.setBuyer_add1("12");
			bv.setBuyer_add2("12");
			bv.setBuyer_comtel("12");
			bv.setBuyer_fax("12");
			bv.setBuyer_mail("12");
			bv.setBuyer_charger("12");
			bv.setBuyer_telext("12");
			ServiceResult result = service.createBuyer(bv);
			System.out.println(result);
			
//			System.out.println(buyer.getBuyer_add1());
//			String viewPath = "/WEB-INF/views/buyerMain.jsp";
//			request.getRequestDispatcher(viewPath).forward(request, response);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
