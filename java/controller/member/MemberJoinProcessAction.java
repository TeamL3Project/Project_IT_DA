package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.DB.Member;
import Member.DB.MemberDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
													throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		int userBirth = Integer.parseInt(request.getParameter("userBirth"));
		String userGender = request.getParameter("userGender");					//성별
		String userPhone = request.getParameter("userPhone");					//전화번호
		String userAddress1 = request.getParameter("userAddress1");				//주소
		String userAddress2 = request.getParameter("userAddress2");				//상세주소
		String userPost = request.getParameter("userPost");						//우편번호
		String userEmail = request.getParameter("userEmail");					//이메일
		String userCategory = request.getParameter("userCategory");				//관심 카테고리
		
		
		Member m = new Member();
		
		m.setUserId(userId);		m.setUserBirth(userBirth);			m.setUserAddress1(userAddress1);
		m.setUserPw(userPw);		m.setUserGender(userGender);		m.setUserAddress2(userAddress2);
		m.setUserName(userName);	m.setUserPhone(userPhone);			m.setUserPost(userPost);
		m.setUserEmail(userEmail);	m.setUserCategory(userCategory);
		
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.insert(m);
		
		if (result == 0) {										//DB삽입 실패
			System.out.println("회원가입 실패");
			
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			request.setAttribute("message", "회원가입 실패");
			forward.setPath("/main");				//실패하면 그냥 메인페이지로 이동?
			
			return forward;
		
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		
		if (result == 1) {										//DB삽입 성공
			out.println("alert('회원가입을 축하합니다.');");
			out.println("location.href='/main';");	//회원가입 후 메인 페이지로 이동?
		}
		out.println("</script>");
		out.close();
		
		return null;
		
	}

}
