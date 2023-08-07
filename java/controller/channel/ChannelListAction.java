package controller.channel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Channel.DB.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ChannelListAction implements Action {

	 @Override
	    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        // ChannelDAO를 이용하여 데이터베이스로부터 채널 목록을 가져옵니다.
		 	ChannelDAO channelDAO = new ChannelDAO();
	        List<Channel> channelList = channelDAO.getChannelList();

	        // 가져온 채널 목록을 JSP에서 사용할 수 있도록 request 속성에 저장합니다.
	        request.setAttribute("channelList", channelList);

	        // channelList.jsp로 이동하도록 설정합니다.
	        ActionForward forward = new ActionForward();
	        forward.setPath("/channelList.jsp"); // channelList.jsp의 경로를 설정합니다.
	        forward.setRedirect(false); // forward 방식으로 이동하도록 설정합니다.

	        return forward;
	    }
	}