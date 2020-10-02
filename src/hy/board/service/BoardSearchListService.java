package hy.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hy.board.action.Action;
import hy.board.command.ActionCommand;
import hy.board.dao.BoardDAO;

public class BoardSearchListService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String keyword = null;
	
	String keyfield = null;
	keyword = (String) request.getParameter("keyword");
	keyfield = (String) request.getParameter("keyfield");
	System.out.println(keyword + keyfield);
	
	BoardDAO boardDAO = new BoardDAO();
	List<?> searchBoardList = new ArrayList<Object>();
	int page = 1;
	int limit = 10;
	if (request.getParameter("page") != null) {
		page = Integer.parseInt(request.getParameter("page"));
	}
	  int searchlistcount = boardDAO.getSearchListConut(keyword, keyfield);    
	  searchBoardList = boardDAO.getSearchList(keyword, keyfield, page, limit);    
	  int maxpage = (int) ((double) searchlistcount / limit + 0.95);    
	  int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;    
	  int endpage = startpage + 10 - 1;      
	  if (endpage > maxpage) {        
		  endpage = maxpage;      
		  }    
	  request.setAttribute("page", page);    
	  request.setAttribute("maxpage", maxpage);    
	  request.setAttribute("startpage", startpage);    
	  request.setAttribute("endpage", endpage);    
	  request.setAttribute("searchlistcount", searchlistcount);    
	  request.setAttribute("searchBoardlist", searchBoardList);    
	  request.setAttribute("keyword", keyword);    
	  request.setAttribute("keyfield", keyfield);    
	  ActionCommand actionCommand = new ActionCommand( );    
	  actionCommand.setRedirect(false);    
	  actionCommand.setPath("./board/board_search_list.jsp");    
	  return actionCommand; 
	}

}
