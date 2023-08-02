package controller.channel;

import controller.action.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("*.chl")
public class ChannelFrontController extends HttpServlet {
	private static final long serialVersionUID = 2L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		int lastURI = RequestURI.lastIndexOf('/');
		String command = RequestURI.substring(lastURI);
		System.out.println(command);

		ActionForward forward = null;
		Action action = null;

		switch (command) {
			case "/BoardList.chl":
//				action = new BoardListAction();
				break;

		}

		forward = action.execute(request, response);
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				System.out.println(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("test/html;charset=utf-8");
		doProcess(request, response);
	}
}
