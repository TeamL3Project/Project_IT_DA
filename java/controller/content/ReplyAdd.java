package controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Content.DB.Reply;
import Content.DB.ReplyDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ReplyAdd implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
        System.out.println("RequestURI = " + requestURI);
        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);
		ReplyDAO redao = new ReplyDAO();
		
		Reply re = new Reply();
		
		re.setReplyWriter(request.getParameter("replywriter"));
		re.setReplyContent(request.getParameter("replycontent"));
		System.out.println("replycontent=" + re.getReplyContent());
		
		re.setBoardNum(Integer.parseInt(request.getParameter("Reply_board_num")));
		
		int ok = redao.replyInsert(re);
		response.getWriter().print(ok);
		
		return null;
	}

}
