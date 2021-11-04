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

/**
 * Servlet implementation class SignIn
 */
@WebServlet({ "/Signin", "/signin" })
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SignIn() {
		// TODO Auto-generated constructor stub
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
		User user = new User();
		user = UserDAO.signIn(username, password);
		if (user.getIdUser() == 0) {
			request.setAttribute("message", "fail");
			response.sendRedirect("login?status=fail");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("idUser", user.getIdUser());
			session.setAttribute("username", user.getUserName());
			session.setAttribute("firstname", user.getFirstName());
			session.setAttribute("lastname", user.getLastName());
			session.setAttribute("email", user.getUserMail());
			session.setAttribute("phone", user.getUserPhone());
			session.setAttribute("address", user.getUserAddress());
			session.setAttribute("avatar", user.getAvatar());
			response.sendRedirect("home");
		}
	}

}
