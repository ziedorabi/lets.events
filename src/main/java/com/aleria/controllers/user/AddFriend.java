package com.aleria.controllers.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aleria.services.FriendRequestDAO;

/**
 * Servlet implementation class AddFriend
 */
@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFriend() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		boolean loggedIn = session != null && session.getAttribute("idUser") != null;
		if (!loggedIn) {
			response.sendRedirect("login");
		} else {
			int idUserOne = (int) session.getAttribute("idUser");
			int idUserTwo = Integer.parseInt(request.getParameter("idUser"));
			int check = FriendRequestDAO.checkFriendship(idUserOne, idUserTwo);
			int status = 0;
			if (check != 1 && check != 4) {
				status = FriendRequestDAO.sendRequest(idUserOne, idUserTwo);
				if (status > 0) {
					request.setAttribute("check", 3);
					request.getRequestDispatcher("profile.jsp").forward(request, response);
				} else {
					request.setAttribute("check", 0);
					request.getRequestDispatcher("profile.jsp").forward(request, response);
				}
			}
		}
	}

}
