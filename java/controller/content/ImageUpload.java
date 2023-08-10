package controller.content;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import controller.action.Action;
import controller.action.ActionForward;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ImageUpload implements Action {
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
    														throws ServletException, IOException {
        
        String chNum = req.getHeader("channelNum");
        String conNum =  req.getHeader("contentNum");
        
        String saveFolder = "/image/content/" + chNum + '/' + conNum + '/';
        String realFolder = req.getServletContext().getRealPath(saveFolder);

        File makeFolder = new File(realFolder);
        if (!makeFolder.exists()) {
            makeFolder.mkdirs();
        } else {
            System.out.println("이미 있는 디렉토리 이름: " + makeFolder.getPath());
        }

        System.out.println("realFolder = " + realFolder);

        int fileSize = 10 * 1024 * 1024;
        try {
            MultipartRequest multi = new MultipartRequest(req, realFolder, fileSize, "utf-8",
                    new DefaultFileRenamePolicy());

            String url = "http://localhost/webapp" + saveFolder + multi.getFilesystemName("upload");
            JsonObject j = new JsonObject();
            j.addProperty("url", url);
            resp.getWriter().print(j);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}


