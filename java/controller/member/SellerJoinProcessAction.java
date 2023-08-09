package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Channel.DB.ChannelBean;
import Channel.DB.ChannelDAO;
import Member.DB.Seller;
import Member.DB.SellerDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class SellerJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
																throws ServletException, IOException {
		
		//Seller_Form에서 입력받는 데이터들
		String userId = request.getParameter("userid");
		String chName = request.getParameter("channel");					//채널명
		String sellerProfile = request.getParameter("profile");				//채널 프로필
		String sellerPhone = request.getParameter("phone");					//업무용 연락처
		String sellerEmail = request.getParameter("email");					//업무용 이메일
		int cate_id = Integer.parseInt(request.getParameter("category"));	//카테고리 Id
		String sellerInfo = request.getParameter("info");					//채널 소개글
		String sellerJoindate = request.getParameter("sellerJoindate");		//가입일시
		
		
		HttpSession session = request.getSession();
		session.setAttribute("userid", userId);					//로그인한 id값을 세션에서 가져옴
		
		//각 테이블에 값을 세팅
		Seller s = new Seller();
		s.setSellerPhone(sellerPhone);			s.setSellerEmail(sellerEmail);
		s.setSellerJoindate(sellerJoindate);
		
		ChannelBean ch = new ChannelBean();
		ch.setChinfo(sellerInfo);			ch.setCate_id(cate_id);
		ch.setChprofile(sellerProfile);		ch.setChname(chName);
		
		
		SellerDAO sdao = new SellerDAO();
		ChannelDAO chdao = new ChannelDAO();
		
		int result1 = sdao.insertSeller(s, userId);
		int result2 = chdao.insertChOwner(ch, userId);
		
		ActionForward forward = new ActionForward();
		
		if (result1 == 0 || result2 == 0) {								//DB에 삽입되지 않은 경우
			System.out.println("판매회원가입 실패");
			
			forward.setRedirect(true);
			request.setAttribute("message", "판매회원가입 실패");
			forward.setPath("/project");
		
			return forward;
		}
		
		
		if (result1 == 1 && result2 == 1) {								//DB에 삽입된 경우
			forward.setRedirect(true);
			request.setAttribute("message", "판매회원가입 성공");
            forward.setPath("/project");
            
		}
		
		
		
		return null;
	
	}

}
