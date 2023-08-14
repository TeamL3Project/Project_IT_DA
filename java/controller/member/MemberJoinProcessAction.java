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

		String userId = request.getParameter("id");
		String userPw = request.getParameter("password");
		String userName = request.getParameter("name");
		
		  ActionForward forward = new ActionForward();

		    HttpSession session = request.getSession();
		    userId = (String) session.getAttribute("userId");

		    String saveFolder = "image/Member";
		    int fileSize = 5 * 1024 * 1024;

		    ServletContext sc = request.getServletContext();
		    String realFolder = sc.getRealPath(saveFolder);

		    String userFolder = realFolder + File.separator + userId;
		    folderService.createFolder(userFolder);

		    int channelNum = Integer.parseInt(request.getParameter("channelNum"));
		    realFolder += File.separator + channelNum;
		    folderService.createFolder(realFolder);

		    // dateService 객체를 생성하여 현재 날짜 정보 문자열을 반환하는 
		    // toDay() 메소드를 호출하여 반환된 값을 realFolder 변수에 추가
		    dateService dateService = new dateService();
		    realFolder += File.separator + util.dateService.toDay();

		    // 이전과 같이 util 대신 dateService로 수정합니다.
		    folderService.createFolder(realFolder);

		    MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

		    // 프로필 사진 업로드 및 경로 처리
		    Part profilePart = request.getPart("profile"); // 프로필 사진 Part 가져오기
		    String userProfileFileName = getFileName(profilePart); // 업로드된 파일 이름 가져오기
		    String userProfilePath = realFolder + File.separator + userProfileFileName; // 프로필 사진 파일 경로 생성

		    // 프로필 사진 경로를 세션에 저장
		    session.setAttribute("userProfilePath", userProfilePath);

		// 날짜 문자열 확인 및 파싱
		String dateOfBirthStr = request.getParameter("date_birth");
		LocalDate dateOfBirth = null;
		if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
			dateOfBirth = LocalDate.parse(dateOfBirthStr);
		}

		String userGender = request.getParameter("gender"); // 성별
		String userPhone = request.getParameter("phone"); // 전화번호
		String userAddress1 = request.getParameter("address1"); // 주소 
		String userAddress2 = request.getParameter("address2"); // 상세주소
		String userPost = request.getParameter("zip_code"); // 우편번호
		String userEmail = request.getParameter("email"); // 이메일
		String userCategory = request.getParameter("category"); // 관심 카테고리
		String userProfile = multi.getFilesystemName("userProfile");  
		
		// 가입일 문자열 확인 및 파싱
		String userJoindateStr = request.getParameter("joindate");
		LocalDate userJoindate = null;
		if (userJoindateStr != null && !userJoindateStr.isEmpty()) {
			userJoindate = LocalDate.parse(userJoindateStr);
		}

		String statusIdParam = request.getParameter("status_id");
		int statusId = (statusIdParam != null && !statusIdParam.trim().isEmpty()) ? Integer.parseInt(statusIdParam) : 0;

		// 수정일 문자열 확인 및 파싱
		String updateDateStr = request.getParameter("update_date");
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


	public String getFileName(Part part) {
		String contentDispositionHeader = part.getHeader("content-disposition");
		String[] elements = contentDispositionHeader.split(";");
		for (String element : elements) {
			if (element.trim().startsWith("filename")) {
				return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}

