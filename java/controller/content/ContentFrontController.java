package controller.content;


import controller.action.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("*.co")
public class ContentFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestuestURI = request.getRequestURI();
		System.out.println("RequestURI = " + requestuestURI);
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		int lastURI = requestuestURI.lastIndexOf('/');
		String command = requestuestURI.substring(lastURI);
		System.out.println(command);

		ActionForward forward = null;
		Action action = null;

		switch (command) {
			case "/login.co":
//				action = new MemberLoginAction();
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request,response);
	}
}
