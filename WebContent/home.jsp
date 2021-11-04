<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.aleria.entities.*" %>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <% List<Invitation> listInvitations = new ArrayList<Invitation>() ; 
      listInvitations = (ArrayList<Invitation>) request.getAttribute("listUpcomingEvents"); 
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Let's - Index</title>
<%@include file="ressource.html" %>
<link rel="stylesheet" href="css/home.css">

</head>
<body>
	<%@include file="header.html" %>

<main> 
	<div class="page-event">
  <div class="container">
    <div class="upcoming-sec">
      <div class="heading">Les événements à venir</div>
    </div>
    <div class="upcoming-event-list">
     <% if (listInvitations.size()>0) {
     for (Invitation i:listInvitations) {
     	Event event = new Event() ;
     	event = i.getEvent() ;%>
      <div class="event-block">
        <div class="row"  style="flex-wrap: nowrap !important">
          <div class="col-lg-2 sec-1">
            <table>
              <tr>
                <td>
                  <div class="month"><%= event.getEventDate().getMonth() %></div>
                  <div class="month-date-devider"></div>
                  <div class="date"><%=event.getEventDate().getDayOfMonth() %></div>
                </td>
                <td class="title"></td>
              </tr>
            </table>
          </div>
          <div class="col-lg-5  sec-2">
            <img style="padding-left:10% ;padding-right:10%; margin-top: 10%" src=<%if(event.getEventType().equals("Sortie")) { out.println("img/outing.webp"); } else { out.println("img/inside.webp");} %> />
          </div>
          <div class="col-lg-5 sec-3">
            <div class="title"><%= event.getEventTitle() %></div>
            <div class="venue">
              <table>
                <tr>
                  <td><i class="fa fa-address-book"></i></td>
                  <td>
                    <div><a href="profile?idUser=<%=i.getHost().getIdUser()%>"><%=i.getHost().getUserName() %></a></div>
                    <div class="dim-color">
                      <i class="fa fa-map-marker"></i> <%= event.getAddress() %>
                    </div>
                  </td>
                </tr>
              </table>
            </div>
            <div class="time">
              <table>
                <tr>
                  <td><i class="fa fa-clock-o"></i></td>
                  <td>
                    <div><%=event.getEventDate() %></div>
                    <div data-livestamp="1517054400" class="dim-color"></div>
                  </td>
                </tr>
              </table>
            </div>
            <div class="sort-story">"<%=event.getDescription() %>" - <%= i.getHost().getUserName() %></div>
            <div class="group-of-btn">
              <a href="event?idEvent=<%=event.getIdEvent() %>" target="_target" class="btn book-ticket" >Voir Plus</a>
            </div>
          </div>
        </div>
      </div>
      <%} }else { %>
      		<h6 style="padding: 35%">Pas d'événements pour le futur</h6>
      		<%} %>
    </div>
  </div>
</div>
</main>
<%@include file="footer.html" %>


</body>
</html>