package controller.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Channel.DB.ChannelBean;
import Channel.DB.ChannelDAO;
import ContentCategory.DB.ContentCategoryBean;
import ContentCategory.DB.ContentCategoryDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ContentWrite implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		ChannelBean cb = new ChannelBean();
		ContentCategoryBean ccdata = new ContentCategoryBean();
		ContentCategoryDAO cbcdao = new ContentCategoryDAO();
		
		
		String chnum = request.getParameter("chnum").trim();
		int chNum = Integer.parseInt(chnum);
		cb.setChnum(chNum);
		
		List<ContentCategoryBean> cbctlist = new ArrayList<ContentCategoryBean>();
		cbctlist = cbcdao.getCategoryNameList(chNum);
		
		request.setAttribute("cbctlist", cbctlist);
		//chboardcategory 테이블에서 카테고리 네임 가져오기
		//request.setAttribute() 메서드를 이용하여 카테고리 네임 담기
		
		
		forward.setRedirect(false); // 포워딩 방식으로 주소가 바뀌지 않아요
		forward.setPath("/content/content_write.jsp");
		return forward;
	}
}