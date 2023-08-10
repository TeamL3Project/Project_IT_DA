package controller.content;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import controller.action.Action;
import controller.action.ActionForward;

import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.dateService.toDay;
import static util.folderService.createFolder;

public class ImageUpload implements Action {
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String channelNum = req.getHeader("channelNum");
//        String contentNum =  req.getHeader("contentNum");
        String contextPath = req.getContextPath();
        String realFolder = "";
        String saveFolder = "/image/content/";
        String url = contextPath+saveFolder + channelNum+"/"+ toDay()+'/';
        int fileSize = 10 * 1024 * 1024;
        ServletContext sc = req.getServletContext();
        realFolder = sc.getRealPath(saveFolder);
        realFolder += channelNum+'/';  // 채널 폴더 생성
        createFolder(realFolder);
        realFolder += toDay()+'/';  // 개시날짜 폴더 생성
        createFolder(realFolder);



        System.out.println("realFolder = " + realFolder);

        try {
            MultipartRequest multi = new MultipartRequest(req, realFolder, fileSize, "utf-8",
                    new DefaultFileRenamePolicy());

            JsonObject j = new JsonObject();
            j.addProperty("url", url);
            resp.getWriter().print(j);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
//        realFolder += contentNum;  // 콘텐트 폴더 생성
//        createFolder(realFolder);
    }
}


