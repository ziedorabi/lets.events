package com.aleria.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.aleria.entities.Event;

public class EventDAO {

	public static int createEvent(Event event) {
		int status = 0;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = DB.getCon();
			ps = con.prepareStatement(
					"INSERT INTO event (title, description, date, address,type) VALUES (? , ?, ? ,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, event.getEventTitle());
			ps.setString(2, event.getDescription());
			ps.setObject(3, event.getEventDate());
			ps.setString(4, event.getAddress());
			ps.setString(5, event.getEventType());
			status = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
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

	public static Event getEvent(int idEvent) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Event event = new Event();
		try {
			con = DB.getCon();
			ps = con.prepareStatement("SELECT  title, description, date, address, type from event WHERE idEvent=?");
			ps.setInt(1, idEvent);
			rs = ps.executeQuery();
			while (rs.next()) {
				event.setEventTitle(rs.getString(1));
				event.setDescription(rs.getString(2));
				event.setEventDate(rs.getObject(3, LocalDateTime.class));
				event.setAddress(rs.getString(4));
				event.setEventType(rs.getString(5));
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
		return event;

	}

}
