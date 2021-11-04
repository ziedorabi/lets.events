<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="com.aleria.entities.*" %>
    <%@page import = "java.util.List" %>
    <%@page import = "java.util.ArrayList" %>
    
    <% List<Invitation> listInvitation = new ArrayList<Invitation>() ; 
    listInvitation = (ArrayList<Invitation>) request.getAttribute("listInvitation"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Mes invitations</title>
	<%@include file="ressource.html" %>
    <link rel="stylesheet" href="css/invitations.css">

</head>
<body>
<%@include file = "header.html" %>
<div class="container">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-xs-5">
                        <h2>Mes invitations réçues</b></h2>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Hôte</th>
                        <th>Title</th>						
                        <th>Date </th>
                        <th>Description</th>
                        <th>Adresse</th>
                        <th>Type</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <% if (listInvitation.size() > 0) {
                for (Invitation i:listInvitation) {%>
                    <tr>
                        <td><img src="<%= i.getHost().getAvatar() %>" class="avatar" alt="Avatar"></td>
                        <td><a href="profile?idUser=<%=i.getHost().getIdUser() %>"><%=i.getHost().getUserName() %></a></td>
                        <td><%=i.getEvent().getEventTitle()%></td>                        
                        <td><%=i.getEvent().getEventDate() %></td>
                        <td><%=i.getEvent().getDescription() %></td>
                        <td><%=i.getEvent().getAddress() %></td>
                        <td><%=i.getEvent().getEventType() %></td>
                         <td>
                            <a href="handleInvitation?action=accept&idInvitation=<%=i.getIdInvitation() %>" class="settings" title="Settings""><i class="material-icons">&#10003;</i></a>
                       
                            <a href="handleInvitation?action=decline&idInvitation=<%=i.getIdInvitation() %>" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                        </td>
                    </tr>
                    <%}} else {  %><tr><td align="center" colspan="8"><h2>Pas d'invitations.</h2></td></tr><%} %>
                </tbody>
            </table>

        </div>
    </div>        
</div>     
<%@include file="footer.html" %>
</body>
</html>