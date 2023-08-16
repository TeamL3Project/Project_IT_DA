package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;
import controller.action.ActionForward;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
														throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"success\": true}");
		
		return null;
	}

}
