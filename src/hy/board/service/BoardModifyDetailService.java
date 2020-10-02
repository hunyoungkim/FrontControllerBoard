package hy.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hy.board.action.Action;
import hy.board.command.ActionCommand;
import hy.board.dao.BoardDAO;
import hy.board.model.BoardDTO;

public class BoardModifyDetailService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		boardDTO = boardDAO.getDetail(num);
		if (boardDTO == null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
		request.setAttribute("boardDTO", boardDTO);
		actionCommand.setRedirect(false);
		actionCommand.setPath("./board/board_modify.jsp");
		return actionCommand;
	}

}
