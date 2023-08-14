package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import controller.action.ActionForward;


@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		int lastURI = RequestURI.lastIndexOf('/');
		String command = RequestURI.substring(lastURI);
		System.out.println(command);
		System.out.println(request.getParameter("password")+"testtest");
		
		ActionForward forward = null;
		Action action = null;
		
		switch (command) {
			case "/login.me":
				action = new MemberLoginAction();
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
			case "/loginProcess.me":
				action = new MemberLoginProcessAction();
				break;	
			case "/logout.me":
				action = new MemberLogoutAction();
				break;
			case "/sellerjoin.me":
				action = new SellerJoinAction();
				break;
			case "/sellerjoinprocess.me":	
				action = new SellerJoinProcessAction();
				break;
			case "/search.me":
				action = new SearchAction();
				break;
			case "/myPage.me":
				action = new MyPageAction();
				break;
			case "/infoUpdate.me":
				action = new InfoUpdateAction();
				break;
			case "/infoUpdateProcess.me":
				action = new InfoUpdateProcessAction();
				break;
		}
		
		forward = action.execute(request, response);
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
				
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				
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
