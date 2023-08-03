package controller.channel;

import controller.action.ActionForward;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 2L;

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String RequestURI = req.getRequestURI();
		int lastURI = RequestURI.lastIndexOf('/');
		String command = RequestURI.substring(lastURI);
		System.out.println(command);

		ActionForward forward = null;
		Action action = null;

		switch (command) {
			case "/BoardList.bo":
				action = new BoardListAction();
				break;
			case "/BoardWrite.bo":
				action = new BoardWriteAction();
				break;
			case "/BoardAddAction.bo":
				action = new BoardAddAction();
				break;
			case "/BoardDetailAction.bo":
				action = new BoardDetailAction();
				break;
			case "/BoardModifyView.bo":
				action = new BoardModifyView();
				break;
			case "/BoardModifyAction.bo":
				action = new BoardModifyAction();
				break;
			case "/BoardReplyView.bo":
				action = new BoardReplyView();
				break;
			case "/BoardReplyAction.bo":
				action = new BoardReplyAction();
				break;
			case "/BoardDeleteAction.bo":
				action = new BoardDeleteAction();
				break;
			case "/BoardFileDown.bo":
				action = new BoardFileDownAction();
				break;
			case "/CommentAdd.bo":
				action = new CommentAdd();
				break;
			case "/CommentList.bo":
				action = new CommentList();
				break;
			case "/CommentDelete.bo":
				action = new CommentDelete();
				break;
			case "/CommentUpdate.bo":
				action = new CommentUpdate();
				break;
			case "/CommentReply.bo":
				action = new CommentReply();
				break;
		}

		forward = action.execute(req, resp);
		if (forward != null) {
			if (forward.isRedirect()) {
				resp.sendRedirect(forward.getPath()); //페이지URI을 변경하여 이동.
				// 클라이언트에게 새로운 URL로 이동하라는 명령을 보내고, 클라이언트는 새로운 URL로 새로운 요청을 보냅니다.
				// 클라이언트 측에서 이동이 발생하므로, 클라이언트는 서버 측에서 보낸 응답과 새로운 요청을 받습니다.
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				System.out.println(forward.getPath());
				dispatcher.forward(req, resp); //페이지URI을 변경없이 이동.
				// 서버 내부에서 페이지 이동을 수행합니다. 클라이언트는 새로운 요청을 보내지 않고,
				// 원래의 요청과 응답 객체를 유지한 채로 서버 내에서만 이동
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("test/html;charset=utf-8");
		doProcess(req, resp);
	}
}
