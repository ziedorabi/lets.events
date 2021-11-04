<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@page import="com.aleria.entities.*" %>
    <%@page import = "java.util.List" %>
    <%@page import = "java.util.ArrayList" %>
    
    <% List<FriendRequest> listReq = new ArrayList<FriendRequest>() ; 
    listReq = (ArrayList<FriendRequest>) request.getAttribute("listReq"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Mes demandes d'amis</title>
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
                        <h2>Mes invitations reçues</b></h2>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Avatar</th>
                        <th scope="col">Nom d'utilisateur</th>						
                        <th scope="col" colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                <% if (listReq.size() > 0) { 
                int j = 0 ;
                for (FriendRequest req:listReq) {
                	User user = new User() ;
                	user = req.getUserOne();
                	%>
                    <tr>
                        <td scope="row"><%= j+1%></td>
                        <td><a href="#"><img src="<%= user.getAvatar() %>" class="avatar" alt="Avatar"></a></td>
                        <td><a href="profile?idUser=<%=user.getIdUser()%>"><%=user.getUserName() %></a></td>                        
                        <td>
                            <a href="handleRequest?action=accept&idRequest=<%=req.getIdRequest() %>" class="settings" title="Settings""><i class="material-icons">&#10003;</i></a>
                       
                            <a href="handleRequest?action=decline&idRequest=<%=req.getIdRequest() %>" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                        </td>
                    </tr>
                    <%} }else {%><tr><td align="center" colspan="4"><h2>Pas de demandes d'amis.</h2></td></tr><%} %>
                </tbody>
            </table>

        </div>
    </div>        
</div>     
<%@include file="footer.html" %>
</body>
</html>