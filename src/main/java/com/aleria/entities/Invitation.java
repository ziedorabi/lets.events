package com.aleria.entities;

public class Invitation {

	private int idInvitation;
	private Event event;
	private Host host;
	private Attendee attendee;
	private int sentDate;
	private int status;

	public int getIdInvitation() {
		return idInvitation;
	}

	public void setIdInvitation(int idInvitation) {
		this.idInvitation = idInvitation;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public Attendee getAttendee() {
		return attendee;
	}

	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
	}

	public int getSentDate() {
		return sentDate;
	}

	public void setSentDate(int sentDate) {
		this.sentDate = sentDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
