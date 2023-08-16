package controller.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import Content.DB.Reply;
import Content.DB.ReplyDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class ContentList implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ContentDAO ContentDAO = new ContentDAO(); 
		List<ContentBean> contentlist = new ArrayList<ContentBean>();
		ReplyDAO redao = new ReplyDAO();
		Reply replycount = new Reply();
		
        
        int page = 1;
        int limit = 10;
        if(request.getParameter("page")!=null) {
        	page = Integer.parseInt(request.getParameter("page"));
        }
        System.out.println("넘어온 페이지 = " + page);
        
        if(request.getParameter("limit") != null) {
        	limit = Integer.parseInt(request.getParameter("limit"));
        }
        System.out.println("넘어온 limit = " + limit);
        
        int channelnum = Integer.parseInt(request.getParameter("channelnum"));
        String order = request.getParameter("order");
        if(order == null) {
        	order = "desc";
        }
        
        int categoryId = Integer.parseInt(request.getParameter("chcate_id"));
        
        int listcount=0;
        if(categoryId == 0) {  //전체
        	contentlist = ContentDAO.getAllchcatedata(channelnum, order, page, limit);
        	listcount = ContentDAO.getListAllCount(channelnum);
        	replycount = redao.getReplyCount(channelnum);
        	
        }else {  //카테고리
        	contentlist = ContentDAO.getchcatedata(channelnum, categoryId, page, limit, order);
        	listcount = ContentDAO.getListCount(channelnum, categoryId);
        	replycount = redao.getReplyCount(channelnum);
        }
        
        int maxpage = (listcount + limit -1) / limit;
        System.out.println("총 페이지 수 = " + maxpage);
        
        int startpage = ((page-1) / 10) * 10 + 1;
        int endpage = startpage + 10 - 1;
		if(endpage > maxpage)
			endpage = maxpage;
		
		String state = request.getParameter("state");
        
        
		if(state == null) {    
			
			System.out.println("state==null");
			request.setAttribute("page", page); // 현재 페이지 수
			request.setAttribute("maxpage", maxpage); // 최대 페이지 수
			
			// 현재 페이지에 표시할 첫 페이지 수
			request.setAttribute("startpage", startpage);
			
			// 현재 페이지에 표시할 끝 페이지 수
			request.setAttribute("endpage", endpage);
			request.setAttribute("listcount", listcount); // 총 글의 수
			request.setAttribute("replycount", replycount); // 총 댓글의 수
//			
//			// 해당 페이지의 글 목록을 갖고 있는 리스트
			request.setAttribute("contentlist", contentlist);
			request.setAttribute("order", order);
//			request.setAttribute("limit", limit);

			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			
			// 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
			forward.setPath("/content/content_list.jsp");
			
			return forward;
			
		}else {
			System.out.println("state=ajax");
			
			// 위에서 request로 담았던 것을 JsonObject에 담습니다.
			JsonObject object = new JsonObject();
			object.addProperty("page", page); // {"page" : 변수 page의 값} 형식으로 저장
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			
			// JsonObject에 List 형식을 담을 수 있는 addProperty() 존재하지 않습니다.
			// void com.google.gson.JsonObject.add(String property, JsonElement value) 
			//		메서드를 통해서 저장합니다.
			// List 형식을 JsonElement로 바꾸어 주어야 object에 저장할 수 있습니다.
			
			// List => JsonElement
			JsonElement je = new Gson().toJsonTree(contentlist);
			System.out.println("contentlist="+je.toString());
			object.add("contentlist", je);
			
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(object);
			System.out.println(object.toString());
			return null;
		} // else end
	}
}
