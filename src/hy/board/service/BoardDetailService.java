package hy.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hy.board.action.Action;
import hy.board.command.ActionCommand;
import hy.board.dao.BoardDAO;
import hy.board.model.BoardDTO;

public class BoardDetailService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		boardDAO.setReadCountUpdate(num);
		boardDTO = boardDAO.getDetail(num);
		if(boardDTO == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("boardDTO", boardDTO);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setPath("./board/board_view.jsp");
		return actionCommand;
	}

}
