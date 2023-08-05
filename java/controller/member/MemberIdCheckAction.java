package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.DB.MemberDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
														throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		
		int result = dao.isuserId(request.getParameter("userId"));
		
		response.getWriter().print(result);
		System.out.println(result);
		return null;
		
	}

}
