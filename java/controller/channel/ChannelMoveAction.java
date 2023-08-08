package controller.channel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import channel.db.ChannelBean;
import channel.db.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ChannelMoveAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		String chInfo = (String) request.getAttribute("chInfo");
		int lastURI = chInfo.lastIndexOf('/');
		System.out.println(chInfo);
		System.out.println(chInfo.substring(lastURI + 1));

		int chnum = Integer.parseInt(chInfo.substring(lastURI + 1));

		ChannelDAO channeldao = new ChannelDAO();
		ChannelBean channel = channeldao.getChannellist(chnum);

		request.setAttribute("channel", channel);

		forward.setPath("/channel/channelpage.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
