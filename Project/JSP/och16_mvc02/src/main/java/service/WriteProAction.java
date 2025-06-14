package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 1. num, pageNum, writer, email, subject, passwd, content
		int num 		= Integer.parseInt(request.getParameter("num"));
		String pageNum	= request.getParameter("pageNum");
		String writer  	= request.getParameter("writer");
		String email   	= request.getParameter("email");
		String subject 	= request.getParameter("subject");
		String passwd  	= request.getParameter("passwd");
		String content 	= request.getParameter("content");
		// 2. Board board 생성하고 Value Setting
		// num, ref = MAX(num)+1
		// re_level, re_step, readcount = 0
		// reg_date = sysdate
		// 나머진는 입력 받은것 그대로
		Board board = new Board();
		board.setNum(num);
		board.setWriter(writer);
		board.setEmail(email);
		board.setSubject(subject);
		board.setReadcount(0);
		board.setPasswd(passwd);
		board.setRef(num);
		board.setRe_step(0);
		board.setRe_level(0);
		board.setContent(content);
		//IP Set
		board.setIp(request.getRemoteAddr());
		
		// 3. BoardDao bd Instance
		BoardDao bd = BoardDao.getInstance();
		
		// 4. int result = bd.insert(board);
		int result = 0;
		try {
			result = bd.insert(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 5. requset 객체에 result, num, pageNum
		request.setAttribute("result", result);
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		
		return "writePro.jsp";
	}

}
