package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Member.DB.SellerDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class SellerCheckAction implements Action {
	private HttpSession session;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
																throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		SellerDAO sellerdao = new SellerDAO();
		boolean IdCheck = sellerdao.IdCheck(userId); 
		
		if (!IdCheck) {
			response.getWriter().write("false");
			
		} else {
			response.getWriter().write("true");
		}
		
		return null;
		
	}

}
