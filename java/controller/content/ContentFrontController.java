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
        String isContent = content.substring(0,9);
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
            case "/write.co":
            	action = new ContentByCategory();
            	break;
            case "/contents":
                action = new ContentMove();
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
