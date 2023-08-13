package controller.content;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Content.DB.ReplyDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ReplyList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
															throws ServletException, IOException {
		
		ReplyDAO redao = new ReplyDAO();
		
		//{"replyNum" : $("#Reply_board_num").val(), state:state}
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int state = Integer.parseInt(request.getParameter("state"));
		
		int listcount = redao.getListCount(boardNum);
		
		JsonArray jarray = redao.getReplyList(boardNum, state);
		
		JsonObject object = new JsonObject();
		object.addProperty("listcount", listcount);
		
		JsonElement JE = new Gson().toJsonTree(jarray);
		object.add("replylist", JE);
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(object.toString());
		System.out.println(object.toString());
		
		return null;
	}

}
