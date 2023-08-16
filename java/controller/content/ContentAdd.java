package controller.content;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Content.DB.ContentBean;
import Content.DB.ContentDAO;
import ContentCategory.DB.ContentCategoryBean;
import ContentCategory.DB.ContentCategoryDAO;
import Tag.DB.TagBean;
import Tag.DB.TagDAO;
import controller.action.Action;
import controller.action.ActionForward;
import util.messeage;

public class ContentAdd implements Action {
		private static final int Join_Fail = 0;
		private static final int Join_Success = 1;
		private HttpSession session;

		private ContentBean setContentFromRequest(MultipartRequest multi) {
			ContentBean content = new ContentBean();

			content.setBoardTitle(multi.getParameter("boardTitle"));
			content.setBoardContent(multi.getParameter("boardContent"));
			content.setThumbNail(multi.getParameter("thumbNail"));




			return content;

		}

		private ContentCategoryBean setContentCategoryFromRequest(MultipartRequest multi) {
			ContentCategoryBean catedata = new ContentCategoryBean();

			catedata.setChcate_Name(multi.getParameter("chcate_Name"));

			return catedata;

		}
		private TagBean setTagFromRequest(MultipartRequest multi) {
			TagBean tdata = new TagBean();

			tdata.setTagname(multi.getParameter("tagname"));

			return tdata;

		}

	public ActionForward execute(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		session = request.getSession();
		int chnum = (int) session.getAttribute("chnum");
		String userId = (String) session.getAttribute("userId");
		int chcate_id = (int) session.getAttribute("chcate_id");
		String saveFolder = "image/ContentUpload";
		int fileSize = 5*1024*1024;

		ServletContext sc = request.getServletContext();		//실제 저장 경로를 지정
		String realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder = " + realFolder);

		//userid별 디렉토리 생성
		String chFolder = realFolder + File.separator + chnum;

		File directory =  new File(chFolder);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		try {MultipartRequest multi = new MultipartRequest(
			request,chFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());


			ContentBean condata = setContentFromRequest(multi);
			ContentCategoryBean catedata = setContentCategoryFromRequest(multi);
			TagBean tdata = setTagFromRequest(multi);

			ContentDAO condao = new ContentDAO();
			ContentCategoryDAO catedao = new ContentCategoryDAO();
			TagDAO tdao = new TagDAO();

//			int contentResult = condao.InsertContent(chnum, chcate_id);
			List<ContentCategoryBean> cateResult = catedao.chcategorySelect(chnum);
			boolean tagResult = tdao.tagInsert(tdata);


			if (contentResult == Join_Fail  || tagResult == false) {//DB에 삽입되지 않은 경우
				System.out.println(messeage.Join.FAIL);

				forward.setRedirect(true);
				request.setAttribute("message", messeage.Join.FAIL);
				forward.setPath(request.getContextPath()+"/main");

			}else if (contentResult == Join_Fail  && tagResult == true) {	//DB에 삽입된 경우
				forward.setRedirect(true);
				request.setAttribute("message", messeage.Join.SUCCESS);
	            forward.setPath(request.getContextPath()+"/main");

			}


		}catch (Exception e) {
			e.printStackTrace();

		}

		return forward;

	}

}
