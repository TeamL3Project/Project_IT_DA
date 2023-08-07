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
        String content = request.getRequestURI().substring(8, 16);
        String command = "";
        System.out.println(content);
        if (content.equals("contents")) {
            command = request.getRequestURI().substring(7, 16);
            String chInfo = request.getRequestURI().substring(17);
            request.setAttribute("chInfo", chInfo);
        } else {
            String requestuestURI = request.getRequestURI();
            System.out.println("RequestURI = " + requestuestURI);
            String contextPath = request.getContextPath();
            System.out.println("contextPath = " + contextPath);
            int lastURI = requestuestURI.lastIndexOf('/');
            command = requestuestURI.substring(lastURI);
        }
        System.out.println(command);

        ActionForward forward = null;
        Action action = null;

        switch (command) {
            case "/login.co":
//				action = new MemberLoginAction();
                break;
            case "/upload.co":
                action = new ImageUpload();
                break;
            case "/contentregit.co":
                action = new ContentRegist();
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
