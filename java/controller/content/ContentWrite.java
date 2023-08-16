package controller.content;

import Channel.DB.ChannelBean;
import ContentCategory.DB.ContentCategoryBean;
import ContentCategory.DB.ContentCategoryDAO;
import controller.action.Action;
import controller.action.ActionForward;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentWrite implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		ChannelBean cb = new ChannelBean();
		ContentCategoryDAO cbcdao = new ContentCategoryDAO();
		
		String chnum = request.getParameter("chnum").trim();
		int chNum = Integer.parseInt(chnum);
		cb.setChnum(chNum);
		
		List<ContentCategoryBean> cbctlist = new ArrayList<ContentCategoryBean>();
		cbctlist = cbcdao.getCategoryNameList(chNum);

		session.setAttribute("chnum", cb.getChnum());
		request.setAttribute("cbctlist", cbctlist);

		forward.setRedirect(false);
		forward.setPath("/content/content_write.jsp");
		return forward;
	}
}