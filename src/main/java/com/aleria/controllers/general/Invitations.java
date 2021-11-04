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

import com.aleria.entities.Invitation;
import com.aleria.services.InvitationDAO;

/**
 * Servlet implementation class Friends
 */
@WebServlet({ "/Invitations", "/invitations" })
public class Invitations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Invitations() {
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
			int idAttendee = (int) session.getAttribute("idUser");
			List<Invitation> listInv = new ArrayList<Invitation>();
			listInv = InvitationDAO.getReceivedInvitations(idAttendee);
			request.setAttribute("listInvitation", listInv);
			request.getRequestDispatcher("invitations.jsp").forward(request, response);
		}
	}

}
