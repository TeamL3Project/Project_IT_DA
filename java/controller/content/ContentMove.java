package controller.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import ContentCategory.DB.ContentCategoryBean;
import ContentCategory.DB.ContentCategoryDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ContentMove implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionForward forward = new ActionForward();
        String chInfo = (String) request.getAttribute("chInfo");
        int lastURI = chInfo.lastIndexOf('/');
        System.out.println(chInfo);
        System.out.println(chInfo.substring(lastURI+1));
        int boardNum = Integer.parseInt(chInfo.substring(lastURI+1));
        ContentDAO dao = new ContentDAO();
        ContentBean co = dao.contentSelect(boardNum);
        
		
		//int chcate_Name = Integer.parseInt(request.getParameter("chcate_Name"));
		ContentCategoryDAO ccdao = new ContentCategoryDAO();
		ContentCategoryBean ccdata = new ContentCategoryBean();
		//ccdata = ccdao.getContentCateName("chname");
        
        request.setAttribute("co", co);
		request.setAttribute("ccd", ccdata);
		//request.setAttribute("ccdlist", ccdlist);
        forward.setPath("/content/content_detail.jsp");
        forward.setRedirect(false);
        return forward;
    }
}
