package hy.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import hy.board.action.Action;
import hy.board.command.ActionCommand;
import hy.board.dao.BoardDAO;
import hy.board.model.BoardDTO;

public class BoardReplyService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		int result = 0;
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		//System.out.println("보드리플 서비스");
		try {
			MultipartRequest multiReques = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());
			boardDTO.setNum(Integer.parseInt(multiReques.getParameter("num")));
			boardDTO.setName(multiReques.getParameter("name"));
			boardDTO.setPass(multiReques.getParameter("pass"));
			boardDTO.setSubject(multiReques.getParameter("subject"));
			boardDTO.setContent(multiReques.getParameter("content"));
			boardDTO.setAnswer_num(Integer.parseInt(multiReques.getParameter("answer_num")));
			boardDTO.setAnswer_lev(Integer.parseInt(multiReques.getParameter("answer_lev")));
			boardDTO.setAnswer_seq(Integer.parseInt(multiReques.getParameter("answer_seq")));
			boardDTO.setAttached_file(multiReques.getFilesystemName((String) multiReques.getFileNames().nextElement()));

			result = boardDAO.boardReply(boardDTO);
			if (result == 0) {
				System.out.println("답변 실패");
				return null;
			}
			System.out.println("답변 성공");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardDetail.do?num=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actionCommand;
	}

}
