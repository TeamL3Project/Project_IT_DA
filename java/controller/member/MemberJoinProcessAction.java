package controller.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Member.DB.Member;
import Member.DB.MemberDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 // 이미지 저장 디렉토리 설정
	    String saveFolder = "image/Member";
	    int fileSize = 5 * 1024 * 1024;

	    // 서버 저장 경로 설정
	    ServletContext sc = request.getServletContext();
	    String realFolder = sc.getRealPath(saveFolder);

	    // MultipartRequest 객체 생성 및 요청 값 처리
	    MultipartRequest multi = new MultipartRequest(
	        request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

	    // 사용자 폴더 생성
	    String userId = multi.getParameter("id");
	    String userFolder = realFolder + File.separator + userId;
	    util.folderService.createFolder(userFolder);

	    // 날짜별 폴더 생성
	    userFolder += File.separator + util.dateService.toDay();
	    util.folderService.createFolder(userFolder);

	    // 사용자 정보 파라미터 처리
	    System.out.println(userId);
	    String userPw = multi.getParameter("password");
	    String userName = multi.getParameter("name");

	    ActionForward forward = new ActionForward();

	    // 파일 이동 처리 부분 추가
	    String paramName = "";
	    String fileName = "";
	    Enumeration<?> files = multi.getFileNames();

	    while (files.hasMoreElements()) {
	        paramName = (String) files.nextElement(); // 파일 업로드 파라미터 이름 조회
	        fileName = multi.getFilesystemName(paramName); // 업로드된 파일 이름 조회
	        if (fileName == null) { // 파일이 존재하지 않을 경우, 다음 파일 검사
	            continue;
	        }

	        File sourceFile = new File(realFolder + File.separator + fileName); // 원본 경로 설정
	        File destinationFile = new File(userFolder + File.separator + fileName); // 실제 사용자 날짜별 폴더 경로 설정

	        if (sourceFile.exists()) { // 원본 파일이 존재할 경우
	            sourceFile.renameTo(destinationFile); // 사용자 날짜별 폴더로 이동
	        }
	    }
		
		
		

		
	    

	    // 생년월일 문자열 파싱 및 LocalDate 형 변환
		String dateOfBirthStr = multi.getParameter("date_birth");
		LocalDate dateOfBirth = null;
		if (dateOfBirthStr != null && !dateOfBirthStr.isEmpty()) {
			dateOfBirth = LocalDate.parse(dateOfBirthStr);
		}

		// 사용자 정보 변수 할당
		String userGender = multi.getParameter("gender"); // 성별
		String userPhone = multi.getParameter("phone"); // 전화번호
		String userAddress1 = multi.getParameter("address1"); // 주소 
		String userAddress2 = multi.getParameter("address2"); // 상세주소
		String userPost = multi.getParameter("zip_code"); // 우편번호
		String userEmail = multi.getParameter("email"); // 이메일
		String userCategory = multi.getParameter("category"); // 관심 카테고리
		String userProfile = multi.getFilesystemName("profile");
		
		//"status_id" 파라미터 값을 정수로 변환하여 statusId 변수에 할당
		String statusIdParam = multi.getParameter("status_id");
		int statusId = (statusIdParam != null && !statusIdParam.trim().isEmpty()) ? Integer.parseInt(statusIdParam) : 0;

		// 수정일 문자열 확인 및 파싱
		String updateDateStr = multi.getParameter("update_date");
		LocalDate updateDate = null;
		if (updateDateStr != null && !updateDateStr.isEmpty()) {
			updateDate = LocalDate.parse(updateDateStr);
		}

		// 현재 시간을 Timestamp 형태로 userJoindate 값으로 설정
		Timestamp userJoindate = new Timestamp(System.currentTimeMillis());
		
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
		m.setUserJoindate(userJoindate);

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
