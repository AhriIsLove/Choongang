package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("WriteFormAction start...");

		try {
			// 답변이 아닌경우
			int num = 0, ref = 0;// 글입력시 변경됨
			int re_level = 0, re_step = 0;// 그대로 사용
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null)
				pageNum = "1";

			// 답변인 경우(원본 글번호가 파라미터로 들어옴)
			if (request.getParameter("num") != null) {
				num = Integer.parseInt(request.getParameter("num"));
				BoardDao bd = BoardDao.getInstance();
				//답변 대상 정보
				Board board = null;
				board = bd.select(num);
				ref = board.getRef();
				re_level = board.getRe_level();
				re_step = board.getRe_step();
			}

			// 파라미터 전달
			request.setAttribute("num", num);
			request.setAttribute("ref", ref);
			request.setAttribute("re_level", re_level);
			request.setAttribute("re_step", re_step);
			request.setAttribute("pageNum", pageNum);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "writeForm.jsp";
	}

}
