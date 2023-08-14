package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Channel.DB.ChannelBean;
import Channel.DB.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
																	throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		ChannelBean channel = new ChannelBean();
		ChannelDAO channeldao = new ChannelDAO();
		
		HttpSession session = request.getSession(true);
		
		String id = (String) session.getAttribute("userId");
		channel.setOwnerid(id);
				
		List<ChannelBean> chlist = new ArrayList<ChannelBean>();
		chlist = channeldao.goMyChannelList(id);

		request.setAttribute("chlist", chlist);
		forward.setRedirect(false); // 포워딩 방식으로 주소가 바뀌지 않아요
		forward.setPath("./member/myPage.jsp");
		return forward;


	}

}
