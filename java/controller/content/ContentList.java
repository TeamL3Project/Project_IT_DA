package controller.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ChannelCategory.DB.ChannelCategoryBean;
import ChannelCategory.DB.ChannelCategoryDAO;
import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ContentList implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ContentDAO ContentDAO = new ContentDAO();
		List<ContentBean> contentlist = new ArrayList<ContentBean>();
        
        int channelnum = Integer.parseInt(request.getParameter("channelnum"));
        String order = request.getParameter("order");
        if(order == null) {
        	order = "asc";
        }
        
        int categoryId = Integer.parseInt(request.getParameter("chcate_id"));
        
        int listcount=0;
        if(categoryId == 0) {  //전체
        	contentlist = ContentDAO.getAllchcatedata(channelnum, order);
        	listcount = contentlist.size();
        }else {  //카테고리
        	contentlist = ContentDAO.getchcatedata(channelnum, categoryId);        	
        	listcount = ContentDAO.getListCount(channelnum, categoryId);
        }
        
 
        
        
			request.setAttribute("listcount", listcount); // 총 글의 수
//			
//			// 해당 페이지의 글 목록을 갖고 있는 리스트
			request.setAttribute("contentlist", contentlist);
//			
//			request.setAttribute("limit", limit);

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			
			// 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
			forward.setPath("/content/content_list.jsp");
			
			return forward; // BoardFrontController.java로 리턴합니다.
			
//		}else {
//			System.out.println("state=ajax");
//			
//			// 위에서 request로 담았던 것을 JsonObject에 담습니다.
//			JsonObject object = new JsonObject();
//			object.addProperty("page", page); // {"page" : 변수 page의 값} 형식으로 저장
//			object.addProperty("maxpage", maxpage);
//			object.addProperty("startpage", startpage);
//			object.addProperty("endpage", endpage);
//			object.addProperty("listcount", listcount);
//			object.addProperty("limit", limit);
//			
//			// JsonObject에 List 형식을 담을 수 있는 addProperty() 존재하지 않습니다.
//			// void com.google.gson.JsonObject.add(String property, JsonElement value) 
//			//		메서드를 통해서 저장합니다.
//			// List 형식을 JsonElement로 바꾸어 주어야 object에 저장할 수 있습니다.
//			
//			// List => JsonElement
//			JsonElement je = new Gson().toJsonTree(contentlist);
//			System.out.println("contentlist="+je.toString());
//			object.add("contentlist", je);
//			
//			response.setContentType("application/json;charset=utf-8");
//			response.getWriter().print(object);
//			System.out.println(object.toString());
//			return null;
//		} // else end
	}
}
