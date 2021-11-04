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

import com.aleria.entities.Event;
import com.aleria.entities.Host;
import com.aleria.entities.User;
import com.aleria.services.EventDAO;
import com.aleria.services.FriendRequestDAO;
import com.aleria.services.HostDAO;
import com.aleria.services.InvitationDAO;

/**
 * Servlet implementation class EventDetails
 */
@WebServlet({ "/EventDetails", "/event" })
public class EventDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventDetails() {
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
			int idEvent = Integer.parseInt(request.getParameter("idEvent"));
			int idHost = HostDAO.getIdHost(idEvent);
			if (FriendRequestDAO.checkFriendship(idAttendee, idHost) != 1) {
				response.sendRedirect("home");
			} else {
				Event event = new Event();
				Host host = new Host();
				event = EventDAO.getEvent(idEvent);
				host = HostDAO.getHost(idEvent);
				List<User> listAccepted = new ArrayList<User>();
				List<User> listDeclined = new ArrayList<User>();
				List<User> listWaiting = new ArrayList<User>();
				listAccepted = InvitationDAO.getListAccepted(idEvent);
				listDeclined = InvitationDAO.getListDeclined(idEvent);
				listWaiting = InvitationDAO.getListWaiting(idEvent);
				request.setAttribute("listWaiting", listWaiting);
				request.setAttribute("listDeclined", listDeclined);
				request.setAttribute("listAccepted", listAccepted);
				request.setAttribute("eventDetails", event);
				request.setAttribute("host", host);
				request.getRequestDispatcher("event.jsp").forward(request, response);
			}
		}
	}

}
