package com.aleria.controllers.host;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aleria.entities.Event;
import com.aleria.services.AttendeeDAO;
import com.aleria.services.EventDAO;
import com.aleria.services.InvitationDAO;

/**
 * Servlet implementation class AddEvent
 */
@WebServlet({ "/AddEvent", "/addevent" })
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEvent() {
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

		HttpSession session = request.getSession(false);

		boolean loggedIn = session != null && session.getAttribute("idUser") != null;
		if (!loggedIn) {
			response.sendRedirect("signin");
		} else {
			request.getRequestDispatcher("createEvent.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);

		boolean loggedIn = session != null && session.getAttribute("idUser") != null;
		if (!loggedIn) {
			response.sendRedirect("login");
		} else {
			String guests[];
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String eventType = request.getParameter("eventType");
			String address = request.getParameter("address");
			String eventDate = request.getParameter("date");
			guests = request.getParameterValues("guest[]");
			int idHost = (int) session.getAttribute("idUser");
			int status = 0;
			LocalDateTime date = LocalDateTime.parse(eventDate);
			Event event = new Event();
			event.setEventTitle(title);
			event.setDescription(description);
			event.setEventType(eventType);
			event.setEventDate(date);
			event.setAddress(address);
			int idEvent = 0;
			idEvent = EventDAO.createEvent(event);
			for (int i = 0; i < guests.length; i++) {
				if (guests[i] != null || guests[i].isEmpty())
					status = InvitationDAO.createInvitation(AttendeeDAO.getIdAttendees(guests[i]), idHost, idEvent);
			}
			if (status > 0) {
				request.setAttribute("message", "success");
				response.sendRedirect("EventDetails?idEvent=" + idEvent);
			} else {
				request.setAttribute("message", "fail");
				response.sendRedirect("AddEvent");
			}
		}
	}

}
