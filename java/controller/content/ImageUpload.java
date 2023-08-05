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
import java.io.File;
import java.io.IOException;

public class ImageUpload implements Action {
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionForward forward = new ActionForward();
        String chNum = req.getHeader("chNum");
        String conNum =  req.getHeader("conNum");
        String realFolder = "";
        String saveFolder = "/image/content/";
        String url = "http://localhost/webapp"+saveFolder + chNum +'/' +conNum +'/';
        int fileSize = 10 * 1024 * 1024;
        ServletContext sc = req.getServletContext();
        realFolder = sc.getRealPath(saveFolder);

        realFolder += chNum+'/';  // 채널 폴더 생성
        File makeChFolder = new File(realFolder);
        if(!makeChFolder.exists()) {
            makeChFolder.mkdir();
        } else {
            System.out.println("이미 있는 디렉토리 이름 : " + makeChFolder.getPath());
        }
        realFolder += conNum;  // 콘텐트 폴더 생성
        File makeConFolder = new File(realFolder);
        if(!makeConFolder.exists()) { // 디렉토리가 없는 경우
           makeConFolder.mkdir();
        } else { // 이미 있는 경우
            System.out.println("이미 있는 디렉토리 이름 : " + makeConFolder.getPath());
        }

        System.out.println("realFolder = " + realFolder);
        try {
            MultipartRequest multi = new MultipartRequest(req, realFolder, fileSize, "utf-8",
                    new DefaultFileRenamePolicy());
            JsonObject j = new JsonObject();
            j.addProperty("url", url+ multi.getFilesystemName("upload"));
            resp.getWriter().print(j);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}


