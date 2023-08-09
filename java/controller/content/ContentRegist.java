package controller.content;

import Content.DB.ContentDAO;
import Content.DB.ContentBean;
import controller.action.Action;
import controller.action.ActionForward;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContentRegist implements Action {

    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ContentDAO dao = new ContentDAO();
        ContentBean con = new ContentBean();
        con.setBoardTitle(req.getParameter("boardtitle"));
        con.setBoardContent(req.getParameter("boardcontent"));
        con.setWriter(req.getParameter("writer"));
        con.setChcate_id(Integer.parseInt(req.getParameter("chcate_id")));
        con.setChNum(Integer.parseInt(req.getParameter("chnum")));
        dao.contentInsert(con);


        return null;
    }
}
