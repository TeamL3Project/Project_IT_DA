package controller.main;

import ChannelCategory.DB.ChannelCategoryBean;
import ChannelCategory.DB.ChannelCategoryDAO;
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
        ChannelCategoryDAO channelCategoryDao = new ChannelCategoryDAO();
        int firstConnect = 0;
        List<ContentBean> popcontentList = dao.popcontentSelect();
        List<ContentBean> contentSelectBycategory = dao.contentSelectBycategory(firstConnect);
        List<ChannelCategoryBean> channelCategoryBeanList = channelCategoryDao.selectInfo();
        request.setAttribute("popcontentList",popcontentList);
        request.setAttribute("contentSelectBycategory",contentSelectBycategory);
        request.setAttribute("channelCategory",channelCategoryBeanList);

        forward.setRedirect(false);
        forward.setPath("main/protomain.jsp");
        return forward;
    }
}
