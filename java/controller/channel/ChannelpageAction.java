package controller.channel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Channel.DB.ChannelBean;
import Channel.DB.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ChannelpageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ChannelDAO channeldao = new ChannelDAO();
		List<ChannelBean> channelpage = new ArrayList<ChannelBean>();

		
//		channelpage = channeldao.getChannelList();

		req.setAttribute("channelpage", channelpage);

		
		ActionForward forward = new ActionForward();
		forward.setPath("channel/channelpage.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
