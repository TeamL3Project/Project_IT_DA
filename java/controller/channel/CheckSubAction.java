package controller.channel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Channel.DB.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class CheckSubAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
		int chnum = Integer.parseInt(request.getParameter("chnum"));

        ChannelDAO channeldao = new ChannelDAO();
        boolean isSubscribed = channeldao.checksub(userId, chnum);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(isSubscribed);

        return null;
    }
}
