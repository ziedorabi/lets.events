package com.aleria.controllers.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aleria.entities.User;
import com.aleria.services.UserDAO;
import com.aleria.tools.Validators;

/**
 * Servlet implementation class Singup
 */
@WebServlet({ "/Signup", "/signup" })
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Signup() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String usermail = request.getParameter("email");
		String userphone = request.getParameter("phone");
		String userAddress = request.getParameter("address");
		String avatar = request.getParameter("gender");

		boolean usernameSafe = Validators.validateUserName(username);
		boolean usermailSafe = Validators.validateMail(usermail);
		boolean userphoneSafe = Validators.validateNumber(userphone);
		boolean userpassSafe = Validators.validatePassword(password);
		if (!usermailSafe || !userphoneSafe || !usernameSafe || !userpassSafe) {
			request.getRequestDispatcher("login").include(request, response);
		} else {
			User user = new User();
			user.setUserName(username);
			user.setPassword(password);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setUserMail(usermail);
			user.setUserPhone(userphone);
			user.setUserAddress(userAddress);
			user.setAvatar(avatar);
			int status = 0;
			status = UserDAO.signUp(user);
			if (status > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("idUser", user.getIdUser());
				session.setAttribute("username", user.getUserName());
				session.setAttribute("firstName", user.getFirstName());
				session.setAttribute("lastName", user.getLastName());
				session.setAttribute("userMail", user.getUserMail());
				session.setAttribute("userPhone", user.getUserPhone());
				session.setAttribute("avatar", user.getAvatar());
				response.sendRedirect("home");
			}
		}

	}

}
