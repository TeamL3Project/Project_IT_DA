package controller.content;

import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.action.Action;
import controller.action.ActionForward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ContentByCategory implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ContentDAO contentDao = new ContentDAO();
        List<ContentBean> contentsBycategory;
        String hasCategoryNum = request.getParameter("categoryNum");
        int pageCount = Integer.parseInt(request.getParameter("pageCount"));
        if(hasCategoryNum.equals("0")){
            contentsBycategory = contentDao.contentSelectBycategory(pageCount);
        } else {
            int categoryNum = Integer.parseInt(hasCategoryNum);
            contentsBycategory = contentDao.contentSelectBycategory(categoryNum, pageCount);
        }

        JsonArray JsonArray = new JsonArray();
        for (ContentBean content : contentsBycategory) {
            JsonObject j = new JsonObject();
            j.addProperty("boardNum", content.getBoardNum());
            j.addProperty("chNum", content.getChNum());
            j.addProperty("writer", content.getWriter());
            j.addProperty("boardTitle", content.getBoardTitle());
            j.addProperty("boardContent", content.getBoardContent());
            j.addProperty("boardHeart", content.getBoardHeart());
            j.addProperty("chCate_id", content.getChCate_id());
            j.addProperty("boardOpen", content.getBoardOpen());
            j.addProperty("boardNore", content.getBoardNore());
            j.addProperty("boardDate", String.valueOf(content.getBoardDate()));
            j.addProperty("boardUpdate", String.valueOf(content.getBoardUpdate()));
            j.addProperty("thumbNail", content.getThumbNail());
            JsonArray.add(j);
            System.out.println(j);
        }
        
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print(JsonArray);
        return null;
    }
}
