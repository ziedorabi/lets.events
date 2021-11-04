package com.aleria.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendeeDAO {

	public static int getIdAttendees(String username) {
		int idAttendee = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DB.getCon();
			ps = con.prepareStatement("SELECT idUser from user WHERE username = ? ");
			ps.setInt(1, idAttendee);
			rs = ps.executeQuery();
			while (rs.next()) {
				idAttendee = rs.getInt(1);
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

		return idAttendee;
	}

}
