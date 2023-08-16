package controller.member;

import java.io.File;
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
import util.messeage;

public class SellerJoinProcessAction implements Action {
	private static final int Join_Fail = 0;
	private static final int Join_Success = 1;
	private HttpSession session;

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
		session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		String saveFolder = "image/MemberUpload";
		int fileSize = 5 * 1024 * 1024;

		ServletContext sc = request.getServletContext(); // 실제 저장 경로를 지정
		String realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder = " + realFolder);

		// userid별 디렉토리 생성
		String userFolder = realFolder + File.separator + userId;

		File directory = new File(userFolder);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		try {
			MultipartRequest multi = new MultipartRequest(request, userFolder, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());

			Seller s = setSellerFromRequest(multi);
			ChannelBean ch = setChannelOwnerFromRequest(multi);

			ChannelDAO channeldao = new ChannelDAO();
			SellerDAO sellerdao = new SellerDAO();

			int SellerResult = sellerdao.insertSeller(s, userId);
			int ChannelResult = channeldao.insertChOwner(ch, userId);

			if (SellerResult == Join_Fail || ChannelResult == Join_Fail) { // DB에 삽입되지 않은 경우
				System.out.println(messeage.Join.FAIL);

				forward.setRedirect(true);
				request.setAttribute("message", messeage.Join.FAIL);
				forward.setPath(request.getContextPath() + "/main");

			} else if (SellerResult == Join_Success && ChannelResult == Join_Success) { // DB에 삽입된 경우
				forward.setRedirect(true);
				request.setAttribute("message", messeage.Join.SUCCESS);
				forward.setPath(request.getContextPath() + "/main");

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return forward;

	}

}
