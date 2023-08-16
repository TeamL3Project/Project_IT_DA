package controller.content;

import static util.countUpService.contentVisitUp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import Content.DB.ReplyDAO;
import Tag.DB.TagBean;
import Tag.DB.TagDAO;
import controller.action.Action;
import controller.action.ActionForward;


public class ContentMove implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		String chInfo = (String) request.getAttribute("chInfo");
		int lastURI = chInfo.lastIndexOf('/');
		int firstURI = chInfo.indexOf('/');
		System.out.println(chInfo);
		System.out.println(chInfo.substring(lastURI + 1));
		System.out.println(chInfo.substring(firstURI, 3));
		int boardNum = Integer.parseInt(chInfo.substring(lastURI + 1));
		String firstURICut = chInfo.substring(firstURI, 3);
		int chnum = Integer.parseInt(firstURICut.substring(1, 3));
		System.out.println("chnum = " + chnum);

		ContentDAO dao = new ContentDAO();
		ContentBean co = dao.contentSelect(boardNum);
		ReplyDAO redao = new ReplyDAO();
		TagDAO tdao = new TagDAO();
		
		List<TagBean> tname = new ArrayList<TagBean>();
		tname = tdao.getTagNameList(boardNum, chnum);
		
		int rcnt = 0;
		rcnt = redao.getListCount(boardNum);

		contentVisitUp(co);
		request.setAttribute("co", co);
		request.setAttribute("rcnt", rcnt);
		request.setAttribute("tname", tname);
		

		forward.setPath("/content/content_detail.jsp");
		forward.setRedirect(false);
		return forward;

	}

}
