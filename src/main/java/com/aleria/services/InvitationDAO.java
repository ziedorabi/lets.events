package com.aleria.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aleria.entities.Event;
import com.aleria.entities.Host;
import com.aleria.entities.Invitation;
import com.aleria.entities.User;

public class InvitationDAO {
	public static List<Invitation> getUpcomingEvent(int idAttendee) {
		List<Invitation> listEvent = new ArrayList<Invitation>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT  e.idEvent,e.title, e.description, e.date, e.address, e.type,u.idUser, u.userName from event e "
							+ "					JOIN invitation i ON e.idEvent = i.idEvent JOIN user u ON i.idHost = u.idUser WHERE ((i.idAttendee = ?) OR (i.idHost= ?))  "
							+ "					 AND Status = ? AND e.date> CURRENT_DATE "
							+ "					ORDER BY e.date");
			ps.setInt(1, idAttendee);
			ps.setInt(2, idAttendee);
			ps.setInt(3, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				Invitation invitation = new Invitation();
				Host host = new Host();
				Event event = new Event();
				event.setIdEvent(rs.getInt(1));
				event.setEventTitle(rs.getString(2));
				event.setDescription(rs.getString(3));
				event.setEventDate(rs.getObject(4, LocalDateTime.class));
				event.setAddress(rs.getString(5));
				event.setEventType(rs.getString(6));
				host.setIdUser(rs.getInt(7));
				host.setUserName(rs.getString(8));
				invitation.setHost(host);
				invitation.setEvent(event);
				listEvent.add(invitation);
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

		return listEvent;
	}

	public static List<Invitation> getReceivedInvitations(int idAttendee) {
		List<Invitation> listInvitation = new ArrayList<Invitation>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT  e.idEvent,e.title, e.description, e.date, e.address, e.type,u.idUser, u.userName, u.avatar, i.idInvite from event e"
							+ " JOIN invitation i ON e.idEvent = i.idEvent JOIN user u ON i.idHost = u.idUser WHERE idAttendee = ? AND "
							+ " e.date >= CURRENT_DATE AND Status = ? " + "ORDER BY e.date ");
			ps.setInt(1, idAttendee);
			ps.setInt(2, 3);
			rs = ps.executeQuery();
			while (rs.next()) {
				Invitation invitation = new Invitation();
				Host host = new Host();
				Event event = new Event();
				event.setIdEvent(rs.getInt(1));
				event.setEventTitle(rs.getString(2));
				event.setDescription(rs.getString(3));
				event.setEventDate(rs.getObject(4, LocalDateTime.class));
				event.setAddress(rs.getString(5));
				event.setEventType(rs.getString(6));
				host.setIdUser(rs.getInt(7));
				host.setUserName(rs.getString(8));
				host.setAvatar(rs.getString(9));
				invitation.setHost(host);
				invitation.setEvent(event);
				invitation.setIdInvitation(rs.getInt(10));
				listInvitation.add(invitation);
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

		return listInvitation;

	}

	public List<Invitation> getDeclinedInvitation(int idAttendee) {
		List<Invitation> listInvitation = new ArrayList<Invitation>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT  e.idEvent,e.title, e.description, e.date, e.address, e.type,u.idUser, u.userName from event e"
							+ " JOIN invitation i ON e.idEvent = i.idEvent JOIN user u ON i.idHost = i.idUser WHERE idAttendee = ? AND "
							+ "  Status = ? " + "ORDER BY e.date ");
			ps.setInt(1, idAttendee);
			ps.setInt(2, 2);
			rs = ps.executeQuery();
			while (rs.next()) {
				Invitation invitation = new Invitation();
				Host host = new Host();
				Event event = new Event();
				event.setIdEvent(rs.getInt(1));
				event.setEventTitle(rs.getString(2));
				event.setDescription(rs.getString(3));
				event.setEventDate(rs.getObject(4, LocalDateTime.class));
				event.setAddress(rs.getString(5));
				event.setEventType(rs.getString(6));
				host.setIdUser(rs.getInt(7));
				host.setUserName(rs.getString(8));
				invitation.setHost(host);
				invitation.setEvent(event);
				listInvitation.add(invitation);
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

		return listInvitation;

	}

	public static List<Invitation> getAttendeeHistory(int idAttendee) {
		List<Invitation> listInvitation = new ArrayList<Invitation>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT  e.idEvent,e.title, e.description, e.date, e.address, e.type,u.idUser, u.userName from event e"
							+ " JOIN invitation i ON e.idEvent = i.idEvent JOIN user u ON i.idHost = u.idUser WHERE idAttendee = ? AND CURRENT_DATE > e.date "
							+ "  AND i.status = ? " + "ORDER BY e.date ");
			ps.setInt(1, idAttendee);
			ps.setInt(2, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				Invitation invitation = new Invitation();
				Host host = new Host();
				Event event = new Event();
				event.setIdEvent(rs.getInt(1));
				event.setEventTitle(rs.getString(2));
				event.setDescription(rs.getString(3));
				event.setEventDate(rs.getObject(4, LocalDateTime.class));
				event.setAddress(rs.getString(5));
				event.setEventType(rs.getString(6));
				host.setIdUser(rs.getInt(7));
				host.setUserName(rs.getString(8));
				invitation.setHost(host);
				invitation.setEvent(event);
				listInvitation.add(invitation);
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

		return listInvitation;

	}

	public static int acceptInvitation(int idInvitaion) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement("UPDATE invitation SET status= ? WHERE idInvite=?");
			ps.setInt(1, 1);
			ps.setInt(2, idInvitaion);
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

	public static int declineInvitation(int idInvitation) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement("UPDATE invitation SET status= ? WHERE idInvite=?");
			ps.setInt(1, 2);
			ps.setInt(2, idInvitation);
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

	public static int createInvitation(int idAttendee, int idHost, int idEvent) {
		Connection con = null;
		PreparedStatement ps = null;
		int status = 0;
		try {
			con = DB.getCon();
			ps = con.prepareStatement("INSERT INTO invitation (idEvent, idHost ,idAttendee) VALUES (?,?,?)");
			ps.setInt(1, idEvent);
			ps.setInt(2, idHost);
			ps.setInt(3, idAttendee);
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

	public static List<Invitation> getHostHistory(int idUser) {
		// TODO Auto-generated method stub
		List<Invitation> listInvitation = new ArrayList<Invitation>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT  e.idEvent,e.title, e.description, e.date, e.address, e.type,u.idUser, u.userName from event e"
							+ " JOIN invitation i ON e.idEvent = i.idEvent JOIN user u ON i.idHost = u.idUser WHERE i.idHost = ? AND CURRENT_DATE > e.date "
							+ "  AND i.status = ? " + "ORDER BY e.date ");
			ps.setInt(1, idUser);
			ps.setInt(2, 0);
			rs = ps.executeQuery();
			while (rs.next()) {
				Invitation invitation = new Invitation();
				Host host = new Host();
				Event event = new Event();
				event.setIdEvent(rs.getInt(1));
				event.setEventTitle(rs.getString(2));
				event.setDescription(rs.getString(3));
				event.setEventDate(rs.getObject(4, LocalDateTime.class));
				event.setAddress(rs.getString(5));
				event.setEventType(rs.getString(6));
				host.setIdUser(rs.getInt(7));
				host.setUserName(rs.getString(8));
				invitation.setHost(host);
				invitation.setEvent(event);
				listInvitation.add(invitation);
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

		return listInvitation;

	}

	public static List<User> getListAccepted(int idEvent) {
		List<User> listAccepted = new ArrayList<User>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT u.idUser , u.username FROM user u JOIN invitation i ON i.idAttendee = u.idUser "
							+ " WHERE i.idEvent = ? AND i.status = ?");
			ps.setInt(1, idEvent);
			ps.setInt(2, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt(1));
				user.setUserName(rs.getString(2));
				listAccepted.add(user);
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

		return listAccepted;

	}

	public static List<User> getListDeclined(int idEvent) {
		List<User> listDeclined = new ArrayList<User>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT u.idUser , u.username FROM user u JOIN invitation i ON i.idAttendee = u.idUser "
							+ " WHERE i.idEvent = ? AND i.status = ?");
			ps.setInt(1, idEvent);
			ps.setInt(2, 2);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt(1));
				user.setUserName(rs.getString(2));
				listDeclined.add(user);
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

		return listDeclined;

	}

	public static List<User> getListWaiting(int idEvent) {
		List<User> listWaiting = new ArrayList<User>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT u.idUser , u.username FROM user u JOIN invitation i ON i.idAttendee = u.idUser "
							+ " WHERE i.idEvent = ? AND i.status = ?");
			ps.setInt(1, idEvent);
			ps.setInt(2, 0);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt(1));
				user.setUserName(rs.getString(2));
				listWaiting.add(user);
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

		return listWaiting;

	}

	public static List<Invitation> getMyUnseenInvite(int idAttendee) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		List<Invitation> listInvite = new ArrayList<Invitation>();

		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"SELECT i.idInvite, u.userName, u.idUser, u.avatar from invitation i JOIN user u ON i.idHost = u.idUser WHERE i.idAttendee = ? and status = ? and seen = ?");
			ps.setInt(1, idAttendee);
			ps.setInt(2, 3);
			ps.setInt(3, 0);
			rs = ps.executeQuery();
			while (rs.next()) {
				Invitation inv = new Invitation();
				Host userOne = new Host();
				inv.setIdInvitation(rs.getInt(1));
				userOne.setUserName(rs.getString(2));
				userOne.setIdUser(rs.getInt(3));
				userOne.setAvatar(rs.getString(4));
				inv.setHost(userOne);
				listInvite.add(inv);
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
		return listInvite;
	}

}
