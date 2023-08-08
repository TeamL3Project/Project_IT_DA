package controller.channel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import channel.db.ChannelBean;
import channel.db.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ChannelDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ChannelDAO channeldao = new ChannelDAO();
		ChannelBean channeldata = new ChannelBean();

		// 글 번호 파라미터 값을 num변수에 저장
		int num = Integer.parseInt(request.getParameter("num"));

		channeldata = channeldao.getChannelDetail(num);

		if (channeldata == null) {
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;

		}
		System.out.println("상세보기 성공");

		// channeldata 객체를 request객체에 저장
		request.setAttribute("channeldata", channeldata);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("channel/channel_profile_detail.jsp");// 자세히 보기 페이지로 이동하기 위해 경로 설정
		return forward;
	}
}
