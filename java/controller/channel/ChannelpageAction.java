package controller.channel;

import Channel.DB.ChannelBean;
import Channel.DB.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChannelpageAction implements Action {

	  @Override
	    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String chNum = request.getParameter("chNum"); // 요청에서 chNum 매개변수를 가져옵니다.

	        ChannelDAO channeldao = new ChannelDAO();
	        List<ChannelBean> channellist = channeldao.getChannelList();

	        // chNum 매개변수를 사용하여 특정 채널 세부 정보를 가져와야 하는 경우
	        // 예시:
	         ChannelBean selectedChannel = channeldao.getChannelDetails(chNum);
	         request.setAttribute("selectedChannel", selectedChannel);

	        request.setAttribute("channellist", channellist);

	        ActionForward forward = new ActionForward();
	        forward.setPath("main/channelList.jsp");
	        forward.setRedirect(false);

	        return forward;
	    }

}
