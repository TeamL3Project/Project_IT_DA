package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Member.DB.MemberDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class MemberUserCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
																throws ServletException, IOException {
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		
		
		String loginId = (String) session.getAttribute("userId");
		
		String DBuserId = mdao.loginok(loginId);
		
		
		
		if (loginId.equals(DBuserId)) {
			forward.setRedirect(false);
			forward.setPath("Seller_Form");
		}
		
		return forward;
	}

}
