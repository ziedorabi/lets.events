package com.aleria.controllers.general;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aleria.entities.User;
import com.aleria.services.FriendRequestDAO;
import com.aleria.services.UserDAO;

/**
 * Servlet implementation class Profile
 */
@WebServlet({ "/Profile", "/profile" })
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profile() {
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
			int idUserTwo = Integer.parseInt(request.getParameter("idUser"));
			String avatar = UserDAO.getGender(idUserTwo);
			if (idUserOne == idUserTwo) {
				request.setAttribute("check", 4);
				User user = new User();
				user.setUserName((String) session.getAttribute("username"));
				user.setFirstName((String) session.getAttribute("firstname"));
				user.setLastName((String) session.getAttribute("lastname"));
				user.setUserPhone((String) session.getAttribute("phone"));
				user.setUserMail((String) session.getAttribute("email"));
				user.setUserAddress((String) session.getAttribute("address"));
				user.setAvatar((String) session.getAttribute("avatar"));
				request.setAttribute("user", user);
				request.getRequestDispatcher("profile.jsp").forward(request, response);
			} else {
				int check = FriendRequestDAO.checkFriendship(idUserOne, idUserTwo);
				if (check == 1) {
					User user = new User();
					user = UserDAO.getProfile(idUserTwo);
					request.setAttribute("check", check);
					request.setAttribute("user", user);
					request.getRequestDispatcher("profile.jsp").forward(request, response);
				} else {
					request.setAttribute("check", check);
					request.setAttribute("idUser", idUserTwo);
					request.setAttribute("avatar", avatar);
					request.getRequestDispatcher("profile.jsp").forward(request, response);
				}

			}
		}
	}

}
