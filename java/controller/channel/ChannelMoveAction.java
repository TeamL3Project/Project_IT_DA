package controller.channel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Channel.DB.ChannelBean;
import Channel.DB.ChannelDAO;
import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import ContentCategory.DB.ContentCategoryBean;
import ContentCategory.DB.ContentCategoryDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ChannelMoveAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		String chInfo = (String) request.getAttribute("chInfo");
		int lastURI = chInfo.lastIndexOf('/');
		System.out.println(chInfo);
		System.out.println(chInfo.substring(lastURI + 1));

		int chnum = Integer.parseInt(chInfo.substring(lastURI + 1));

		ChannelDAO channeldao = new ChannelDAO();
		ContentDAO dao = new ContentDAO();
		ContentCategoryDAO category = new ContentCategoryDAO();

		ChannelBean channel = channeldao.getChannellist(chnum);

		List<ContentBean> channelhome = dao.channelhomeSelect(chnum);
		List<ContentBean> channeldetail = dao.getBoardListByBoardNum(chnum);
		List<ContentCategoryBean> chcategory = category.chcategorySelect(chnum);

		request.setAttribute("channel", channel);
		request.setAttribute("channelhome", channelhome);
		request.setAttribute("channeldetail", channeldetail);
		request.setAttribute("chcategory", chcategory);

		forward.setPath("/channel/channelpage.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
