package com.aleria.controllers.host;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.aleria.entities.FriendRequest;
import com.aleria.entities.Invitation;
import com.aleria.services.FriendRequestDAO;
import com.aleria.services.InvitationDAO;

/**
 * Servlet implementation class ShoutServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ShoutServlet", "/fetch" })
public class Notifications extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Notifications() {
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
			response.sendRedirect("login");
		} else {
			if (!request.getParameter("view").isEmpty()) {
				FriendRequestDAO.getNotif();
			}
			String output = new String();
			List<FriendRequest> listReq = new ArrayList<FriendRequest>();
			List<Invitation> listInvit = new ArrayList<Invitation>();
			listInvit = InvitationDAO.getMyUnseenInvite(2);
			listReq = FriendRequestDAO.getMyUnseenRequest(2);
			if (listReq.size() > 0 || listInvit.size() > 0) {
				int count = listReq.size() + listInvit.size();
				output = "<h2>Notifications - <span>" + count + "</span></h2>\r\n";

				if (listReq.size() > 0) {

					for (FriendRequest req : listReq) {
						output += " <div class=\"notifications-item\"> <img src=\"" + req.getUserOne().getAvatar()
								+ "\" alt=\"img\">\r\n" + "            <div class=\"text\">\r\n"
								+ "                <h4><a href='profile?idUser=" + req.getUserOne().getIdUser() + "'>"
								+ req.getUserOne().getUserName() + " vous envoyé a une demande d'amis.</h4>\r\n"
								+ "                <p><a href='handleRequest?action=accept&idRequest="
								+ req.getIdRequest()
								+ "'><button type='button' class='btn btn-success btn-sm'>Accepter</button>\r\n"
								+ "                <a href='handleRequest?action=decline&idRequest=+"
								+ req.getIdRequest()
								+ "'><button type='button' class='btn btn-danger ml-2 btn-sm'>Refuser</button></a></p>\r\n"
								+ "            </div>\r\n" + "        </div>\r\n";
					}
				}
				if (listInvit.size() > 0) {

					for (Invitation inv : listInvit) {
						output += " <div class=\"notifications-item\"> <img src=\"" + inv.getHost().getAvatar()
								+ "\" alt=\"img\">\r\n" + "            <div class=\"text\">\r\n"
								+ "                <h4><a href='profile?idUser=" + inv.getHost().getIdUser() + "'>"
								+ inv.getHost().getUserName() + " vous envoyé a une invitation à un événement.</h4>\r\n"
								+ "                <p><a href='handleInvitation?action=accept&idInvitation="
								+ inv.getIdInvitation()
								+ "'><button type='button' class='btn btn-success btn-sm'>Accepter</button>\r\n"
								+ "                <a href='handleInvitation?action=decline&idInvitation="
								+ inv.getIdInvitation()
								+ "'><button type='button' class='btn btn-danger ml-2 btn-sm'>Refuser</button></a></p>\r\n"
								+ "            </div>\r\n" + "        </div>\r\n";
					}
				}
			} else {
				output += " <div class=\"notifications-item\">\r\n" + "            <div class=\"text\">\r\n"
						+ "                <h4>Pas de notifications.</h4>\r\n" + "            </div>\r\n"
						+ "        </div>\r\n";
			}
			int count = FriendRequestDAO.getUnreadNotif(2);
			JSONObject json = new JSONObject();
			json.put("notification", output);
			json.put("unseen_notification", count);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(json);
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
