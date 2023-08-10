package controller.channel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Channel.DB.ChannelDAO;
import controller.action.Action;
import controller.action.ActionForward;

public class IdCheckAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청으로부터 input_id 가져오기
        String input_id = request.getParameter("id");

        // ChannelDAO 인스턴스 생성
        ChannelDAO channelDAO = new ChannelDAO();

        // 데이터베이스에서 사용자 아이디에 해당하는 사용자 수 조회
        int count = channelDAO.getUserCountById(input_id); // ChannelDAO에 getUserCountById 메서드를 추가해야 함

        // 클라이언트에게 결과를 응답으로 반환
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        if (count > 0) {
            response.getWriter().write("1"); // 사용중인 아이디
        } else {
            response.getWriter().write("-1"); // 사용 가능한 아이디
        }

        return null;
    }
}
