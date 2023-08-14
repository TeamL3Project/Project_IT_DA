package controller.channel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Channel.DB.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class CanclechsubAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int chnum = Integer.parseInt(request.getParameter("chnum"));

		ChannelDAO channeldao = new ChannelDAO();
		int result = channeldao.subdelete(userId, chnum);

		response.getWriter().print(result);
		return null;
	}

}
