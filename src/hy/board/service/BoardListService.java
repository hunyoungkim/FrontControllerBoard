package hy.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hy.board.action.Action;
import hy.board.command.ActionCommand;
import hy.board.dao.BoardDAO;

public class BoardListService implements Action {

public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	BoardDAO boardDAO = new BoardDAO();

	List<?> boardList = new ArrayList<Object>();

	int page = 1;
	int limit = 10;

	if(request.getParameter("page") != null) {
		page = Integer.parseInt(request.getParameter("page"));
}
	int listcount = boardDAO.getListCount();
	boardList = boardDAO.getBoardList(page, limit);
	
	int maxpage = (int) ((double) listcount / limit + 0.95);
	int startpage = (((int) ((double) page / 10 +0.9)) - 1) *10 +1;
	int endpage = startpage + 10 -1;
	if (endpage > maxpage) {
		endpage = maxpage;
	}
	request.setAttribute("page", page);
	request.setAttribute("maxpage", maxpage);
	request.setAttribute("startpage", startpage);
	request.setAttribute("endpage", endpage);
	request.setAttribute("listcount", listcount);
	request.setAttribute("boardList", boardList);
	ActionCommand actionCommand = new ActionCommand();
	System.out.println("보드리스트서비스 입장");
	actionCommand.setRedirect(false);
	
	actionCommand.setPath("./board/board_list.jsp");
	return actionCommand;
}
}
