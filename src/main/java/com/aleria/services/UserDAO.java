package com.aleria.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aleria.entities.User;

public class UserDAO {

	public static int signUp(User user) {
		Connection con = null;
		int status = 0;
		PreparedStatement ps = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"INSERT INTO user (username, password, firstname, lastname, email, phone, address, avatar)"
							+ "VALUES ( ? , ? , ? , ? , ? , ? , ?,?)");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getUserMail());
			ps.setString(6, user.getUserPhone());
			ps.setString(7, user.getUserAddress());
			ps.setString(8, user.getAvatar());
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public static User signIn(String username, String password) {
		User user = new User();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT idUser, username , firstName, lastName, email, address,phone, avatar from user WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setIdUser(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(4));
				user.setUserMail(rs.getString(5));
				user.setUserAddress(rs.getString(6));
				user.setUserPhone(rs.getString(7));
				user.setAvatar(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public static User getProfile(int idUser) {
		User user = new User();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT idUser, username , firstName, lastName, usermail, useraddress, avatar from user WHERE idUser=?");
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setIdUser(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(4));
				user.setUserMail(rs.getString(5));
				user.setUserAddress(rs.getString(6));
				user.setAvatar(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public static List<String> getAllUser() {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement("SELECT username from user");
			rs = ps.executeQuery();
			while (rs.next()) {

				list.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<String> search(String term, List<String> listUser) {
		List<String> names = new ArrayList<String>();
		for (String username : listUser) {
			if (username.toLowerCase().contains(term.toLowerCase())) {
				names.add(username);
			}
		}
		return names;
	}

	public static String getGender(int idUserTwo) {
		String avatar = new String();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement("SELECT avatar from user");
			rs = ps.executeQuery();
			while (rs.next()) {

				avatar = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return avatar;
	}

	public static int getIdUser(String username) {
		int idUser = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement("SELECT idUser from user WHERE username = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {

				idUser = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return idUser;
	}

}
