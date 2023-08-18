package controller.channel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Channel.DB.ChannelBean;
import Channel.DB.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;


public class ChannelListAction implements Action {

	 @Override
	    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

		  int catIdStr = Integer.parseInt(request.getParameter("categoryNum"));
	        ChannelDAO channelDAO = new ChannelDAO();
	        List<ChannelBean> channelList;

	        if (catIdStr == 0) {
	            // 카테고리 아이디가 없는 경우, 전체 채널 목록을 가져옵니다.
	            channelList = channelDAO.getChannelList();
	        } else {
	            // 카테고리 아이디가 있는 경우, 해당 카테고리 아이디의 채널 목록을 가져옵니다.
	            channelList = channelDAO.getChannelList(catIdStr);
	        }



	        JsonArray jsonArray = new JsonArray();
	        for (ChannelBean channel : channelList) {
	            JsonObject j = new JsonObject();
	            j.addProperty("chNum", channel.getChnum());
	            j.addProperty("ownerId", channel.getOwnerid());
	            j.addProperty("chName", channel.getChname());
	            j.addProperty("chprofile", channel.getChprofile());
	            j.addProperty("chinfo", channel.getChinfo());
	            j.addProperty("cate_id", channel.getCate_id());
	            j.addProperty("chvisit", channel.getChvisit());
	            jsonArray.add(j);
	            System.out.println(j);
	        }
	        response.setContentType("text/html; charset=UTF-8");
	        response.getWriter().print(jsonArray);
	        return null;
	 
	    }
	}