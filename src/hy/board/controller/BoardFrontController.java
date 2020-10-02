package hy.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hy.board.action.Action;
import hy.board.command.ActionCommand;
import hy.board.service.BoardAddService;
import hy.board.service.BoardDeleteService;
import hy.board.service.BoardDetailService;
import hy.board.service.BoardDownloadService;
import hy.board.service.BoardListService;
import hy.board.service.BoardModifyDetailService;
import hy.board.service.BoardModifyService;
import hy.board.service.BoardReplyMoveService;
import hy.board.service.BoardReplyService;
import hy.board.service.BoardSearchListService;

public class BoardFrontController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requstURL = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requstURL.substring(contextPath.length());

		ActionCommand actionCommand = null;
		System.out.println(contextPath);
		System.out.println(pathURL);
		Action action = null;
		
		if (pathURL.equals("/BoardList.do")) {
			action = new BoardListService();
			
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardWrite.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./board/board_write.jsp");

		} else if (pathURL.equals("/BoardAdd.do")) {
			action = new BoardAddService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BoardDetail.do")) {
			action = new BoardDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BoardDownload.do")) {
			action = new BoardDownloadService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/BoardReply.do")) {
			action = new BoardReplyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BoardReplyMove.do")) {
			action = new BoardReplyMoveService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/BoardModify.do")) {
			action = new BoardModifyDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BoardModifyService.do")) {
			action = new BoardModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/BoardDelete.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./board/board_delete.jsp");
		} else if(pathURL.equals("/BoardDeleteService.do")) {
			action = new BoardDeleteService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(pathURL.equals("/BoardSearchList.do")) {
			action = new BoardSearchListService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(actionCommand != null) {
			if(actionCommand.isRedirect()) {
				response.sendRedirect(actionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

}
