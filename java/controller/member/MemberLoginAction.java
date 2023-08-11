package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import controller.action.ActionForward;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
<<<<<<< HEAD

		forward.setPath(request.getContextPath()+"/main");		//login 성공후 다시 메인페이지를 보여줌(세션에 id값이 있는 상태)

=======
		forward.setPath(request.getContextPath()+"/main");		//login 성공후 다시 메인페이지를 보여줌(세션에 id값이 있는 상태)
>>>>>>> ba0c5004c516f009214794336b21951e1df02959
		return forward;

	}

}
