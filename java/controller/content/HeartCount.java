package controller.content;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import Content.DB.ContentDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class HeartCount implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
															throws ServletException, IOException {
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		ContentDAO dao = new ContentDAO();
		int plusValue = dao.plusheartCount(boardNum);		//기존 boardheart값의 + 1
		int preValue = dao.previousValue(boardNum);			//1증가된 boardheard값을 이전으로 되돌림
		
		JsonObject jobj = new JsonObject();
		jobj.addProperty("success", true);
		
		
		jobj.addProperty("updatedValue", plusValue);
		
		response.setContentType("application/json;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()) {
            out.print(jobj.toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        
        }
		
		return null;
	}

}
