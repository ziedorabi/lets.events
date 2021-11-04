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
 * Servlet implementation class AcceptRequest
 */
@WebServlet("/handleRequest")
public class HandleRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleRequest() {
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
		HttpSession session = request.getSession();
		boolean loggedIn = session != null && session.getAttribute("idUser") != null;
		if (!loggedIn) {
			response.sendRedirect("login");
		} else {
			int idUserOne = (int) session.getAttribute("idUser");
			int idUserTwo = 0;
			if (request.getParameter("idUser") != null)
				idUserTwo = Integer.parseInt(request.getParameter("idUser"));
			int idRequest = 0;
			if (request.getParameter("idRequest") != null)
				idRequest = Integer.parseInt(request.getParameter("idRequest"));
			String action = request.getParameter("action");
			if (action.equals("accept")) {
				FriendRequestDAO.acceptRequest(idRequest);
				response.sendRedirect("requests");
			} else if (action.equals("decline")) {
				FriendRequestDAO.declineRequest(idRequest);
				response.sendRedirect("requests");
			} else if (action.equals("add")) {
				if (request.getParameter("idUser") != null) {
					FriendRequestDAO.sendRequest(idUserOne, idUserTwo);
					response.sendRedirect("profile?idUser=" + idUserTwo);
				}
			} else {
				response.sendRedirect("home");
			}

		}
	}

}
