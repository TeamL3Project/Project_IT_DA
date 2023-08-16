package controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Content.DB.Reply;
import Content.DB.ReplyDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ReplyUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
													throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ReplyDAO redao = new ReplyDAO();
		Reply re = new Reply();
		
		re.setReplyContent(request.getParameter("replycontent"));
		
		re.setReplyNum(Integer.parseInt(request.getParameter("replyNum")));
		
		int ok = redao.replyUpdate(re);
		response.getWriter().print(ok);
		
		return null;
	}

}
