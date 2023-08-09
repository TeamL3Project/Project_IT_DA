package controller.main;

import controller.action.Action;
import controller.action.ActionForward;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/main")
public class MainFrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("test");
        String requestURI = request.getRequestURI();
        System.out.println("RequestURI = " + requestURI);
        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);
        int lastURI = requestURI.lastIndexOf('/');
        String command = requestURI.substring(lastURI);
        System.out.println(command);

        ActionForward forward;
        Action action = null;

        switch (command) {
            case "/main":
				action = new MainEntrance();
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
        doProcess(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doProcess(request,response);
    }
}
