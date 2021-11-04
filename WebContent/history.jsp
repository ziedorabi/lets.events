<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <%@page import="com.aleria.entities.Invitation" %>
    <%@page import="com.aleria.entities.Event" %>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%List<Invitation> listInvitation = new ArrayList<Invitation>() ;
    	listInvitation = (ArrayList<Invitation>) request.getAttribute("listHistory");
    	String mode = request.getParameter("type");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Historique - Invité</title>
	<%@include file="ressource.html" %>
	<link rel="stylesheet" href="css/history.css">

</head>
<body>
<%@include file="header.html" %>
<main>
<div class="content">
    
    <div class="container">
    <%if(mode.equals("host")){ %>
      <h2 class="mb-5">Organisateur</h2>
      <%} else { %><h2 class="mb-5">Invité(e)</h2><%} %>

      <div class="table-responsive">

        <table class="table table-striped custom-table">
          <thead>
            <tr>
              
              <th scope="col">Date</th>
              <th scope="col">Hote</th>
              <th scope="col">Title</th>
              <th scope="col">Adresse</th>
              <th scope="col">Type</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
           <% if (listInvitation.size() > 0 ) {
            for(Invitation i:listInvitation) {
            	Event event = new Event();
            	event = i.getEvent() ;%>
            <tr>
              
              <td><%=event.getEventDate() %></td>
              <td><a href="profile?idUser=<%=i.getHost().getIdUser()%>"><%=i.getHost().getUserName() %></a></td>
              <td>
                <%=event.getEventTitle() %>
              </td>
              <td><%= event.getAddress() %></td>
              <td><%=event.getEventType() %></td>
              <td><a href="eventDetails?idEvent=<%=event.getIdEvent() %>" class="more">Détails</a></td>
            </tr>   
            <%}}else{ %>   <tr><td align="center" colspan="5"><h2>Votre historique est encore vide.</h2></td></tr><%} %>     
          </tbody>
        </table>
      </div>


    </div>

  </div>
  </main>
  <%@include file="footer.html" %>
</body>
</html>