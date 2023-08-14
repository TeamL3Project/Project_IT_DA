package controller.content;


import controller.action.Action;
import controller.action.ActionForward;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"*.co", "/contents/*"})


public class ContentFrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String content = requestURI.substring(contextPath.length());
        System.out.println("content =" + content);
        String isContent = content.substring(0,9);
        System.out.println("isContent =" + isContent);
        String command = "";
        System.out.println(isContent);
        if (isContent.equals("/contents")) {
            command = isContent;
            String chInfo = content.substring(9);
            request.setAttribute("chInfo", chInfo);
        } else {
            requestURI = request.getRequestURI();
            System.out.println("RequestURI = " + requestURI);
            contextPath = request.getContextPath();
            System.out.println("contextPath = " + contextPath);
            int lastURI = requestURI.lastIndexOf('/');
            command = requestURI.substring(lastURI);
        }
        System.out.println(command);

        ActionForward forward = null;
        Action action = null;

        switch (command) {
            case "/upload.co":
                action = new ImageUpload();
                break;
            case "/contentregit.co":
                action = new ContentRegist();
                break;
            case "/contentByCategory.co":
                action = new ContentByCategory();
                break;
            case "/contents":
                action = new ContentMove();
                break;
            case "/contentlist.co":
            	action = new ContentList();
            	break;
            case "/heartCount.co":
            	action = new HeartCount();
            	break;
            case "/contentwrite.co":
            	action = new ContentWrite();
            	break;
            case "/ReplyList.co":
				action = new ReplyList();
				break;
			case "/ReplyDelete.co":
				action = new ReplyDelete();
				break;
			case "/ReplyUpdate.co":
				action = new ReplyUpdate();
				break;
			case "/ReplyReply.co":
				action = new ReplyReply();
				break;
			case "/ReplyAdd.co":
				action = new ReplyAdd();
				break;


        }
        forward = action.execute(request, response);
        if (forward != null) {
            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doProcess(request, response);
    }
}
