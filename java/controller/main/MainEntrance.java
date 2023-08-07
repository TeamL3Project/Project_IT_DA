package controller.main;

import Content.Content;
import Content.ContentDAO;
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
        List<Content> popcontentList = dao.popcontentSelect();

        request.setAttribute("popcontentList",popcontentList);


        forward.setRedirect(false);
        forward.setPath("main/protomain.jsp");
        return forward;
    }
}
