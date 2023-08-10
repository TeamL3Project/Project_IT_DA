package controller.member;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Channel.DB.ChannelBean;
import Channel.DB.ChannelDAO;
import Member.DB.Seller;
import Member.DB.SellerDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class SellerJoinProcessAction implements Action {
	private static final int Join_Fail = 0;
	private static final int Join_Success = 1;

	
	private Seller setSellerFromRequest(MultipartRequest multi) {
		Seller s = new Seller();
		s.setSellerPhone(multi.getParameter("phone"));
		s.setSellerEmail(multi.getParameter("email"));
		s.setSellerJoindate(multi.getParameter("sellerJoindate"));
		
		return s;
		
	}
	
	
	
	
	private ChannelBean setChannelOwnerFromRequest(MultipartRequest multi) {
		ChannelBean ch = new ChannelBean();
		ch.setChinfo(multi.getParameter("info"));
		ch.setCate_id(Integer.parseInt(multi.getParameter("category")));
		ch.setChname(multi.getParameter("channel"));
		ch.setChprofile(multi.getFilesystemName("profile"));
		
		return ch;
	}
	
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
																throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		String saveFolder = "image/MemberUpload";
		int fileSize = 5*1024*1024;								//업로드할 파일의 최대사이즈를 5MB로 설정
		
		ServletContext sc = request.getServletContext();		//실제 저장 경로를 지정
		String realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder = " + realFolder);		//파일 업로드시 저장되는 폴더의 경로
		
		try {MultipartRequest multi = new MultipartRequest(
			request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
		
			String userId = multi.getParameter("userid");
			HttpSession session = request.getSession();
			session.setAttribute("userid", userId);					//로그인한 id값을 세션에서 가져옴
			
			
			Seller s = setSellerFromRequest(multi);
			ChannelBean ch = setChannelOwnerFromRequest(multi);
			
			SellerDAO sellerdao = new SellerDAO();
			ChannelDAO channeldao = new ChannelDAO();
			
			int SellerResult = sellerdao.insertSeller(s, userId);
			int ChannelResult = channeldao.insertChOwner(ch, userId);
			
			
			if (SellerResult == Join_Fail || ChannelResult == Join_Fail) {				//DB에 삽입되지 않은 경우
				System.out.println("판매회원가입 실패");
				
				forward.setRedirect(true);
				request.setAttribute("message", "판매회원가입 실패");
				forward.setPath("/project");
			
			}else if (SellerResult == Join_Success && ChannelResult == Join_Success) {	//DB에 삽입된 경우
				forward.setRedirect(true);
				request.setAttribute("message", "판매회원가입 성공");
	            forward.setPath("/project");
	            
			}
			
			
		
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return null;
		
	}

}
