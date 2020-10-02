package hy.board.dao;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class dbc
 */
@WebServlet("/dbc")
public class dbc extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection connnection = null;
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			System.out.println(dataSource + "연결되었습니다.");
			
			connnection =dataSource.getConnection();
			System.out.println(connnection);
		} catch (Exception e) {
			System.out.println("DataBase 연결 실패" + e);
			// TODO: handle exception
			return;
		}
		
	}

	

}
