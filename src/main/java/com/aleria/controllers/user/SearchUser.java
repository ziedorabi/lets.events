package com.aleria.controllers.user;

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

import com.aleria.services.UserDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class SearchUser
 */
@WebServlet({ "/SearchUser", "/searchuser" })
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchUser() {
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

			Gson gson = new Gson();
			String term = request.getParameter("term");
			List<String> u = new ArrayList<String>();
			u = UserDAO.getAllUser();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(UserDAO.search(term, u)));
			out.flush();
			out.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			HttpSession session = request.getSession();
			boolean loggedIn = session != null && session.getAttribute("idUser") != null;
			if (!loggedIn) {
				response.sendRedirect("login");
			} else {

				String username = request.getParameter("username");
				int idUser = UserDAO.getIdUser(username);
				if (idUser > 0) {
					response.sendRedirect("profile?idUser=" + idUser);
				} else {
					response.sendRedirect("home");
				}
			}
		}

	}

}
