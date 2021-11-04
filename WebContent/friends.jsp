<%@page import="javax.swing.plaf.ListUI"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="com.aleria.entities.*" %>
    <%@page import = "java.util.List" %>
    <%@page import = "java.util.ArrayList" %>
    
    <% List<User> listUser = new ArrayList<User>() ; 
    listUser = (ArrayList<User>) request.getAttribute("listFriends"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Mes amis</title>
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
                        <h2>Liste d'amis</b></h2>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Photo</th>
                        <th>Nom d'utilisateur</th>
                        <th>Prénom</th>						
                        <th>Nom </th>
                    </tr>
                </thead>
                <tbody>
               <% if (listUser.size() > 0)  { 
                int j = 0 ;
                for (User user:listUser) {%>
                    <tr>
                        <td><%= j+1%></td>
                        <td><a href="profile?idUser=<%=user.getIdUser() %>" ><img src="<%= user.getAvatar() %>" class="avatar" alt="Avatar"></a></td>
                        <td><%=user.getUserName()%></td>                        
                        <td><%=user.getFirstName() %></td>
                        <td><%=user.getLastName()%></td>
                    </tr>
                    <%} }else{ %> <tr><td align="center" colspan="5"><h2>La liste d'amis est encore vide.</h2></td></tr><%} %>
                </tbody>
            </table>

        </div>
    </div>        
</div>     
<%@include file="footer.html" %>
</body>
</html>