package controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ContentDetail implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		  
		ContentDAO contentdao = new ContentDAO();
		ContentBean contentdata = new ContentBean();
		
		// 글번호 파라미터 값을 num 변수에 저장합니다.
		int num = Integer.parseInt(request.getParameter("num"));
		
		// 내용을 확인할 글의 조회수를 증가시킵니다.
		contentdao.setReadCountUpdate(num);
		
		// 글의 내용을 DAO에서 읽은 후 얻은 결과를 boarddata 객체에 저장합니다.
		contentdata = contentdao.getDetail(num);
		
		//boarddata=null; //error 테스트를 위한 값 설정
		//DAO에서 글의 내용을 읽지 못했을 겨우 null을 반환합니다.
		if(contentdata==null) {
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		
		//boarddata 객체를 request 객체에 저장합니다.
		request.setAttribute("contentdata", contentdata);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../content/content_detail.jsp");
		//글 내용보기 페이지로 이동하기 위해 경로를 설정합니다.
		return forward;
	}

}
