package hy.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import hy.board.action.Action;
import hy.board.command.ActionCommand;
import hy.board.dao.BoardDAO;
import hy.board.model.BoardDTO;

public class BoardModifyService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boolean result = false;
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try {
			MultipartRequest multiRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multiRequest.getParameter("num"));

			boolean usercheck = boardDAO.isBoardWriter(num, multiRequest.getParameter("pass"));

			if (usercheck == false) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정할 권한이 없습니다.');");
				out.println("location.href='./BoardList.do';");
				out.println("</script>");
				out.close();
				return null;
			}
			boardDTO.setNum(num);
			boardDTO.setName(multiRequest.getParameter("name"));
			boardDTO.setSubject(multiRequest.getParameter("subject"));
			boardDTO.setContent(multiRequest.getParameter("content"));
			boardDTO.setAttached_file(
					multiRequest.getFilesystemName((String) multiRequest.getFileNames().nextElement()));
			boardDTO.setOld_file(multiRequest.getParameter("old_file"));

			result = boardDAO.boardModify(boardDTO);
			if (result == false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");

			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardDetail.do?num=" + boardDTO.getNum());
			return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
