package com.aleria.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aleria.entities.FriendRequest;
import com.aleria.entities.User;

public class FriendRequestDAO {

	public static int sendRequest(int idUserOne, int idUserTwo) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement("INSERT INTO friendrequest (idUserOne, idUserTwo, status) VALUES (?, ?, ? )");
			ps.setInt(1, idUserOne);
			ps.setInt(2, idUserTwo);
			ps.setObject(3, 3);
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

	public static int acceptRequest(int idRequest) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement("UPDATE friendrequest SET status= ? WHERE idRequest=?");
			ps.setInt(1, 1);
			ps.setInt(2, idRequest);
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

	public static int declineRequest(int idRequest) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement("UPDATE friendrequest SET status= ? WHERE idRequest=?");
			ps.setInt(1, 2);
			ps.setInt(2, idRequest);
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

	public static List<FriendRequest> getMyRequests(int idUserTwo) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		List<FriendRequest> requestList = new ArrayList<FriendRequest>();

		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT r.idRequest, u.userName, u.idUser, u.avatar from friendrequest r JOIN user u ON r.idUserOne = u.idUser WHERE r.idUserTwo = ? and status = ?");
			ps.setInt(1, idUserTwo);
			ps.setInt(2, 3);
			rs = ps.executeQuery();
			while (rs.next()) {
				FriendRequest req = new FriendRequest();
				User userOne = new User();
				req.setIdRequest(rs.getInt(1));
				userOne.setUserName(rs.getString(2));
				userOne.setIdUser(rs.getInt(3));
				userOne.setAvatar(rs.getString(4));
				req.setUserOne(userOne);
				requestList.add(req);
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
		return requestList;
	}

	public static List<FriendRequest> getMyUnseenRequest(int idUserTwo) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		List<FriendRequest> requestList = new ArrayList<FriendRequest>();

		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT r.idRequest, u.userName, u.idUser, u.avatar from friendrequest r JOIN user u ON r.idUserOne = u.idUser WHERE r.idUserTwo = ? and status = ? and seen = ?");
			ps.setInt(1, idUserTwo);
			ps.setInt(2, 3);
			ps.setInt(3, 0);
			rs = ps.executeQuery();
			while (rs.next()) {
				FriendRequest req = new FriendRequest();
				User userOne = new User();
				req.setIdRequest(rs.getInt(1));
				userOne.setUserName(rs.getString(2));
				userOne.setIdUser(rs.getInt(3));
				userOne.setAvatar(rs.getString(4));
				req.setUserOne(userOne);
				requestList.add(req);
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
		return requestList;
	}

	public static List<FriendRequest> getSentRequests(int idUserOne) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		List<FriendRequest> requestList = new ArrayList<FriendRequest>();

		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT r.idRequest, u.userName, u.idUser from friendrequest r JOIN user u ON r.idUserTwo = r.idUser WHERE r.idUserOne = ? and status = ?");
			ps.setInt(1, idUserOne);
			ps.setInt(2, 0);
			rs = ps.executeQuery();
			while (rs.next()) {
				FriendRequest req = new FriendRequest();
				User userTwo = new User();
				req.setIdRequest(rs.getInt(1));
				userTwo.setUserName(rs.getString(2));
				userTwo.setIdUser(rs.getInt(3));
				req.setUserOne(userTwo);
				requestList.add(req);
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
		return requestList;
	}

	public static List<User> getFriendList(int idUser) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		List<User> friendList = new ArrayList<User>();

		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT DISTINCT U.idUser, U.username, U.firstname, U.lastname, U.avatar FROM friendrequest R JOIN user U  ON R.idUserOne = U.idUser OR R.idUserTwo = U.idUser "
							+ "WHERE R.idUserOne = ? OR R.idUserTwo = ? AND R.status = ? GROUP BY U.idUser HAVING U.idUser != ?");
			ps.setInt(1, idUser);
			ps.setInt(2, idUser);
			ps.setInt(3, 1);
			ps.setInt(4, idUser);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString(2));
				user.setIdUser(rs.getInt(1));
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(4));
				user.setAvatar(rs.getString(5));
				friendList.add(user);
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
		return friendList;
	}

	public static int checkFriendship(int idUserOne, int idUserTwo) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT status FROM friendrequest WHERE (idUserOne = ? AND idUserTwo = ? ) OR (idUserOne =? AND idUserTwo = ?)");
			ps.setInt(1, idUserOne);
			ps.setInt(2, idUserTwo);
			ps.setInt(3, idUserTwo);
			ps.setInt(4, idUserOne);
			rs = ps.executeQuery();
			while (rs.next()) {
				status = rs.getInt(1);
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
		return status;
	}

	public static int getNotif() {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement("UPDATE friendrequest SET seen = ? WHERE seen = ?");
			ps.setInt(1, 1);
			ps.setInt(2, 0);
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

	public static int getUnreadNotif(int i) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement("SELECT COUNT(*) FROM friendrequest WHERE seen = ?");
			ps.setInt(1, 0);
			rs = ps.executeQuery();
			while (rs.next()) {
				status = rs.getInt(1);
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
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}
}
