package controller.content;

import Content.ContentDAO;
import Content.Content;
import controller.action.Action;
import controller.action.ActionForward;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContentRegist implements Action {

    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionForward forward = new ActionForward();

        ContentDAO dao = new ContentDAO();
        Content con = new Content();
        con.setBoardTitle(req.getParameter("boardtitle"));
        con.setBoardContent(req.getParameter("boardcontent"));
        con.setWriter(req.getParameter("writer"));
        con.setChcate_id(Integer.parseInt(req.getParameter("chcate_id")));
        con.setChNum(Integer.parseInt(req.getParameter("chnum")));
        dao.contentInsert(con);


        return null;
    }
}
