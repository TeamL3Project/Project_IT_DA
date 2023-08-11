package controller.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Member.DB.Member;
import Member.DB.MemberDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class MemberJoinProcessAction implements Action {
	
	 private void createFolder(String folderPath) {
	        File folder = new File(folderPath);
	        if (!folder.exists()) {
	            folder.mkdirs();
	        }
	    }

	    private String toDay() {
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	        return now.format(formatter);
	    }

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String userId = request.getParameter("id");
		String userPw = request.getParameter("password");
		String userName = request.getParameter("name");
		
		   // 프로필 사진을 저장할 기본 경로 설정
        String saveFolder = "/image/members/";
        String realFolder = "";
        ServletContext sc = request.getServletContext();
        realFolder = sc.getRealPath(saveFolder);

        // 회원 ID 폴더 생성
        realFolder += userId + '/';
        createFolder(realFolder);

        // 업로드 날짜 폴더 생성
        realFolder += toDay() + '/';
        createFolder(realFolder);

        // 프로필 사진 파일을 받아 저장하기
        Part profilePart = request.getPart("profilePicture");
        String originalProfileName = profilePart.getSubmittedFileName();
        String userProfile = null;
        if (originalProfileName != null && !originalProfileName.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            String currentTime = now.format(formatter);
            String uniqueProfileName = currentTime + "_" + originalProfileName;
            userProfile = realFolder + uniqueProfileName;
            profilePart.write(userProfile);
        }
        

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
		m.setUserProfile(userProfile); // 프로필 사진 주소 설정

		MemberDAO mdao = new MemberDAO();
		int result = mdao.insert(m);

		if (result == 0) { // DB삽입 실패
			System.out.println("회원가입 실패");

			ActionForward forward = new ActionForward();
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
