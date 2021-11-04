package com.aleria.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aleria.entities.Host;

public class HostDAO {

	public static int getIdHost(int idEvent) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int idHost = 0;
		try {
			con = DB.getCon();
			ps = con.prepareStatement("SELECT idHost from invitation WHERE idEvent=? LIMIT 1");
			ps.setInt(1, idEvent);
			rs = ps.executeQuery();
			while (rs.next()) {
				idHost = rs.getInt(1);
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
		return idHost;
	}

	public static Host getHost(int idEvent) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Host host = new Host();
		try {
			con = DB.getCon();
			ps = con.prepareStatement("SELECT idHost, username from invitation WHERE idEvent=? LIMIT 1");
			ps.setInt(1, idEvent);
			rs = ps.executeQuery();
			while (rs.next()) {
				host.setIdUser(rs.getInt(1));
				host.setUserName(rs.getString(2));
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
		return host;

	}

}
