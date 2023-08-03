package controller.action;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
	 ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
