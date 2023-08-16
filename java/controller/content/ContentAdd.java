package controller.content;

import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import Tag.DB.TagDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import controller.action.Action;
import controller.action.ActionForward;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import util.dbService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static util.dateService.toDay;
import static util.folderService.createFolder;

public class ContentAdd implements Action {

	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		String realFolder = "";
		String saveFolder = "/image/content/";
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		int fileSize = 5 * 1024 * 1024;
		String Writer = (String) session.getAttribute("userId");
		int channelNum = (int) session.getAttribute("chnum");
		realFolder += String.valueOf(channelNum) + '/';
		createFolder(realFolder);
		realFolder += toDay() + '/';
		createFolder(realFolder);
		ActionForward forward = new ActionForward();

		MultipartRequest multi = new MultipartRequest(
				request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

		String contentText = multi.getParameter("content");
		
		Document doc = Jsoup.parse(contentText);
		Elements paragraphs = doc.select("p");
		String Intro = "";
		for (int i = 0; i < paragraphs.size(); i++) {
			System.out.println(paragraphs.get(i).text());
			boolean text = paragraphs.get(i).text().matches("^(?=.*\\S).*$");
			if (text) {
				Intro += paragraphs.get(i).text();
				if (Intro.length() > 80) {
					break;
				}
			}
		}

		ContentDAO dao = new ContentDAO();
		ContentBean con = new ContentBean();
		con.setChNum(channelNum);
		con.setWriter(Writer);
		con.setBoardTitle(multi.getParameter("boardTitle"));
		con.setBoardContent(multi.getParameter("content"));
		con.setChcate_id(Integer.parseInt(multi.getParameter("categoryId")));
		con.setThumbNail(multi.getFilesystemName("thumbNail"));
		con.setIntro(Intro);
		dao.contentInsert(con);
		List<ContentBean> newcontent = dao.newContentSelect(channelNum);
		int contentNum = newcontent.get(0).getBoardNum();
		int tagLength = 0;
		if (multi.getParameterValues("tagname") != null) {
			tagLength = multi.getParameterValues("tagname").length;
		}
		TagDAO tagDao = new TagDAO();

		for (int i = 0; i < tagLength; i++) {
			String tag = multi.getParameterValues("tagname")[i];
			tagDao.tagInsert(tag, contentNum);
		}

		forward.setRedirect(true);
		forward.setPath(contextPath + "/channels/" + channelNum);
		return forward;
	}

}
