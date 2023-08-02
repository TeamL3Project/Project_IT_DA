package controller.payment;

import controller.action.Action;
import controller.action.ActionForward;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("*.pay")
public class PaymentFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		int lastURI = RequestURI.lastIndexOf('/');
		String command = RequestURI.substring(lastURI);
		System.out.println(command);

		ActionForward forward = null;
		Action action = null;

		switch (command) {
			case "/login.me":
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		doProcess(req,resp);
	}
}
