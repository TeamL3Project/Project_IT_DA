package controller.content;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Content.DB.ReplyDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ReplyDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int replyNum = Integer.parseInt(request.getParameter("num"));
		
		ReplyDAO redao = new ReplyDAO();
		
		int result = redao.replyDelete(replyNum);
		
		PrintWriter out = response.getWriter();
		
		out.println(result);
		out.close();
		
		return null;
	}

}
