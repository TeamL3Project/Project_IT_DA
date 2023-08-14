package controller.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Member.DB.Member;
import Member.DB.MemberDAO;
import controller.action.Action;
import controller.action.ActionForward;
import util.dateService;
import util.folderService;

public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String saveFolder = "image/Member";
		int fileSize = 5 * 1024 * 1024;
		ServletContext sc = request.getServletContext();
		String realFolder = sc.getRealPath(saveFolder);
		
		String userFolder = realFolder + File.separator + dateService.toDay();
		folderService.createFolder(userFolder);
		
		
		MultipartRequest multi = new MultipartRequest(
				request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		String userId = multi.getParameter("id");
		System.out.println(userId);
	    String userPw = multi.getParameter("password");
	    String userName = multi.getParameter("name");
	    
	    ActionForward forward = new ActionForward();




	    // dateService 클래스 메소드로 직접 접근
	    realFolder += File.separator + util.dateService.toDay();

	    folderService.createFolder(realFolder);


	 
		    
		    

		// 날짜 문자열 확인 및 파싱
		String dateOfBirthStr = multi.getParameter("date_birth");
		LocalDate dateOfBirth = null;
		if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
			dateOfBirth = LocalDate.parse(dateOfBirthStr);
		}

		String userGender = multi.getParameter("gender"); // 성별
		String userPhone = multi.getParameter("phone"); // 전화번호
		String userAddress1 = multi.getParameter("address1"); // 주소 
		String userAddress2 = multi.getParameter("address2"); // 상세주소
		String userPost = multi.getParameter("zip_code"); // 우편번호
		String userEmail = multi.getParameter("email"); // 이메일
		String userCategory = multi.getParameter("category"); // 관심 카테고리
		String userProfile = multi.getFilesystemName("userProfile");  
		
		// 가입일 문자열 확인 및 파싱
		String userJoindateStr = multi.getParameter("joindate");
		LocalDate userJoindate = null;
		if (userJoindateStr != null && !userJoindateStr.isEmpty()) {
			userJoindate = LocalDate.parse(userJoindateStr);
		}

		String statusIdParam = multi.getParameter("status_id");
		int statusId = (statusIdParam != null && !statusIdParam.trim().isEmpty()) ? Integer.parseInt(statusIdParam) : 0;

		// 수정일 문자열 확인 및 파싱
		String updateDateStr = multi.getParameter("update_date");
		LocalDate updateDate = null;
		if (updateDateStr != null && !updateDateStr.isEmpty()) {
			updateDate = LocalDate.parse(updateDateStr);
		}

		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPw(userPw);
		m.setUserName(userName);
		m.setDateOfBirth(dateOfBirth);
		m.setUserGender(userGender);
		m.setUserPhone(userPhone);
		m.setUserAddress1(userAddress1);
		m.setUserAddress2(userAddress2);
		m.setUserPost(userPost);
		m.setUserEmail(userEmail);
		m.setUserCategory(userCategory);
		m.setStatusId(statusId);
		m.setUserProfile(userProfile); 

		MemberDAO mdao = new MemberDAO();
		int result = mdao.insert(m);

		if (result == 0) { // DB삽입 실패
			System.out.println("회원가입 실패");

			forward.setRedirect(true);

			request.setAttribute("message", "회원가입 실패");
			forward.setPath(request.getContextPath() + "/main"); // 실패하면 그냥 메인페이지로 이동
			return forward;
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");

		if (result == 1) { // DB삽입 성공
			out.println("alert('회원가입을 축하합니다.');");
			out.println("location.href='" + request.getContextPath() + "/main';"); // 회원가입 후 메인 페이지로 이동
		}
		out.println("</script>");
		out.close();

		return null;
	}
	
}

