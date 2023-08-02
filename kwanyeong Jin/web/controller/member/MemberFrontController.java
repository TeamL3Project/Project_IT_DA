package web.study.webpages_ajax.net.member.action;

import web.study.webpages_ajax.net.member.action.MemberLoginAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		getRequstURI()는 요청의 URI를 반환
		String RequestURI = req.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		String contextPath = req.getContextPath();
		System.out.println("contextPath = " + contextPath);
		int lastURI = RequestURI.lastIndexOf('/');
		String command = RequestURI.substring(lastURI);
		System.out.println(command);

		ActionForward forward = null;
		Action action = null;

		switch (command){
			case "/login.me":
				action = new MemberLoginAction();
				break;
			case "/loginProcess.me":
				action = new MemberLoginProcessAction();
				break;
			case "/join.me":
				action = new MemberJoinAction();
				break;
			case "/idcheck.me":
				action = new MemberIdCheckAction();
				break;
			case "/joinProcess.me":
				action = new MemberJoinProcessAction();
				break;
			case "/logout.me":
				action = new MemberLogoutAction();
				break;
			case "/update.me":
				action = new MemberUpdateAction();
				break;
			case "/updateProcess.me":
				action = new MemberUpdateProcessAction();
				break;
			case "/memberlist.me":
				action = new MemberListAction();
				break;
//			case "/joinProcess2.me":
//				action = new MemberJoinProcessAction2();
//				break;
			case "/memberInfo.me":
				action = new MemberInfoAction();
				break;
//			case "/search.me":
//				action = new MemberSearchAction();
//				break;
			case "/memberDelete.me":
				action = new MemberDeleteAction();
				break;
		}
		forward = action.execute(req, resp);
		if(forward != null){
			if(forward.isRedirect()){
				resp.sendRedirect(forward.getPath()); //페이지URI을 변경하여 이동.
				System.out.println(forward.getPath()+1);
				// 클라이언트에게 새로운 URL로 이동하라는 명령을 보내고, 클라이언트는 새로운 URL로 새로운 요청을 보냅니다.
				// 클라이언트 측에서 이동이 발생하므로, 클라이언트는 서버 측에서 보낸 응답과 새로운 요청을 받습니다.
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				System.out.println(forward.getPath()+2);
				dispatcher.forward(req, resp); //페이지URI을 변경없이 이동.
				// 서버 내부에서 페이지 이동을 수행합니다. 클라이언트는 새로운 요청을 보내지 않고,
				// 원래의 요청과 응답 객체를 유지한 채로 서버 내에서만 이동
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doProcess(req,resp);
	}
}
