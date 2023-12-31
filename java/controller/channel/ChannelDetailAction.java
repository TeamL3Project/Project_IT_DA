package controller.channel;

import Channel.DB.ChannelBean;
import Channel.DB.ChannelDAO;
import Member.DB.Seller;
import Member.DB.SellerDAO;
import controller.action.Action;
import controller.action.ActionForward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChannelDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ChannelDAO channeldao = new ChannelDAO();
		ChannelBean channeldata = new ChannelBean();
		SellerDAO sellerdao = new SellerDAO();
		ChannelBean sellergraph = new ChannelBean();
		
		// 글 번호 파라미터 값을 num변수에 저장
		int num = Integer.parseInt(request.getParameter("channelnum"));

		channeldata = channeldao.getChannelDetail(num);
		Seller sellerdata = sellerdao.getSellerdata(num);
		sellergraph = channeldao.getSellergraph(num);

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
		request.setAttribute("sellerdata", sellerdata);
		request.setAttribute("sellergraph", sellergraph);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("channel/channel_profile_detail.jsp");// 자세히 보기 페이지로 이동하기 위해 경로 설정
		return forward;
	}
}
