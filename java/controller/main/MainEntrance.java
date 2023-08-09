package controller.main;

import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import controller.action.Action;
import controller.action.ActionForward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainEntrance implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionForward forward = new ActionForward();
        ContentDAO dao = new ContentDAO();
        List<ContentBean> popcontentList = dao.popcontentSelect();
//        List<ContentBean> contentSelect_per_cate = dao.contentSelect_per_cate();

        request.setAttribute("popcontentList",popcontentList);
//        request.setAttribute("contentSelect_per_cate",contentSelect_per_cate);


        forward.setRedirect(false);
        forward.setPath("main/protomain.jsp");
        return forward;
    }
}
