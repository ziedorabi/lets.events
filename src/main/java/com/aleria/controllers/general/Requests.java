package com.aleria.controllers.general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aleria.entities.FriendRequest;
import com.aleria.services.FriendRequestDAO;

/**
 * Servlet implementation class Requests
 */
@WebServlet({ "/Requests", "/requests" })
public class Requests extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Requests() {
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
			response.sendRedirect("signin");
		} else {
			int idUserTwo = (int) session.getAttribute("idUser");
			List<FriendRequest> listReq = new ArrayList<FriendRequest>();
			listReq = FriendRequestDAO.getMyRequests(idUserTwo);
			request.setAttribute("listReq", listReq);
			request.getRequestDispatcher("requests.jsp").forward(request, response);
		}
	}

}
