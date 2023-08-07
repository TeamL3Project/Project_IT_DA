package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Member.DB.MemberDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class MemberUserCheckAction implements Action {

	public ActionForward execute (HttpServletRequest request, HttpServletResponse response)
																throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		
		String userId = "";
		Cookie[] cookies = request.getCookies();
		
		
		
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("userId")) {
					userId = cookies[i].getValue();
				}
			}
		}
		
		request.setAttribute("userId", userId);
		
		String loginId = (String) session.getAttribute("userId");
		boolean idOk = mdao.loginok(loginId);
		
		if (idOk == false) {							//db에서 찾을 수 없는 경우
			forward.setRedirect(false);
			request.setAttribute("message", "DB에서 Id를 찾을 수 없습니다");
			forward.setPath("error/error.jsp");
			
		}else {											//db에 있는 경우
			System.out.println("DB 대조 성공");
			forward.setRedirect(true);
			forward.setPath("Seller_Form.jsp");
			
		}
		
		return forward;
	}

}
