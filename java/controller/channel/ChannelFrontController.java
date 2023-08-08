package controller.channel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import controller.action.ActionForward;

@WebServlet(urlPatterns = { "*.chl", "/channels/*" })
public class ChannelFrontController extends HttpServlet {
	private static final long serialVersionUID = 2L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String channel = requestURI.substring(contextPath.length());
		String isChannel = channel.substring(0, 9);
		String command = "";
		System.out.println(isChannel);

		if (isChannel.equals("/channels")) {
			command = isChannel;
			String chInfo = channel.substring(9);
			request.setAttribute("chInfo", chInfo);
		} else {
			requestURI = request.getRequestURI();
			System.out.println("RequestURI= " + requestURI);
			contextPath = request.getContextPath();
			System.out.println("contextPath = " + contextPath);
			int lastURI = requestURI.lastIndexOf('/');
		}

		// 초기화
		ActionForward forward = null;
		Action action = null;

		switch (command) {
			case "/channels":
				action = new ChannelMoveAction();
				break;
			case "/ChannelDetail":
				action = new ChannelDetailAction();
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("test/html;charset=utf-8");
		doProcess(request, response);
	}
}
