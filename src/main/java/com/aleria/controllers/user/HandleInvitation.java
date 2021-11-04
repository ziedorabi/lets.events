package com.aleria.controllers.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aleria.services.InvitationDAO;

/**
 * Servlet implementation class HandleInvitation
 */
@WebServlet({ "/HandleInvitation", "/handleInvitation" })
public class HandleInvitation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleInvitation() {
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
			int idInvitation = Integer.parseInt(request.getParameter("idInvitation"));
			String action = request.getParameter("action");
			if (action.equals("accept")) {
				InvitationDAO.acceptInvitation(idInvitation);
				response.sendRedirect("invitations");
			} else if (action.equals("decline")) {
				InvitationDAO.declineInvitation(idInvitation);
				response.sendRedirect("invitations");
			} else {
				response.sendRedirect("home");
			}

		}
	}

}
