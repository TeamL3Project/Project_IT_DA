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
		ReplyDAO redao = new ReplyDAO();
		Reply re = new Reply();
		
		re.setReplyContent(request.getParameter("replycontent"));
		System.out.println("replycontent=" + re.getReplyContent());
		
		re.setReplyNum(Integer.parseInt(request.getParameter("num")));
		
		int ok = redao.replyUpdate(re);
		response.getWriter().print(ok);
		
		
		return null;
	}

}
