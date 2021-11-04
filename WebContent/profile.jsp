<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <%@ page import="com.aleria.entities.User" %>
    
    <%int check = (int) request.getAttribute("check") ;
    String firstname = new String() ; String lastname = new String() ; String userphone = new String() ; 
    String usermail = new String() ; String  useraddress = new String() ; String gender = new String() ; String username= new String() ; 
    String action = new String(); String idUser  = request.getParameter("idUser");
    if (check == 4) {
    	  User user = new User() ;
    	  user  = (User) request.getAttribute("user"); 
    	  username = user.getUserName() ;
    	  firstname = user.getFirstName();
    	  lastname = user.getLastName();
    	  userphone = user.getUserPhone() ; 
    	  usermail = user.getUserMail();
    	  useraddress = user.getUserAddress() ; 
    	  gender = user.getAvatar() ;
    	  action = "Mon profil";
      } else if (check == 1){ 
    	  User user = new User() ; 
    	  user = (User) request.getAttribute("profile") ; 
    	  username = user.getUserName() ;
    	  firstname = user.getFirstName();
    	  lastname = user.getLastName();
    	  userphone = user.getUserPhone() ; 
    	  usermail = user.getUserMail();
    	  useraddress = user.getUserAddress() ; 
    	  gender = user.getAvatar() ;
    	  action = "<a href='deleteFriend?idUser="+user.getIdUser()+"'>Suppriner</a>";
      } else {
    	  gender = (String) request.getAttribute("avatar");
    	  action = "<a href='sentRequest?idUser="+idUser+">Ajouter</a>" ;} System.out.println(check);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Profil</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
	<%@include file="ressource.html" %>
	<link rel="stylesheet" href="css/profile.css">
</head>
<body>
<%@include file="header.html" %>
<div class="container">

  <div class="middle">
    <img src="<%=gender %>" alt="Photo de profil" class="user-pic" />
    <h4 class="name"><%=username %></h4>
    <h4 class="work">Membre chez Let's</h4>
  </div>
  <%if( check == 0) { %>
	  <div class="bas">
			   <button class="btn-follow"><a href="handleRequest?action=add&idUser=<%=idUser %>">Ajouter</a> </button><br/>
			    <i class="fa fa-lock"></i>
			    <br/>
			    <h4 class="profile-status">This profile is hidden</h4>
			  </div>
			  <%} %>
  
  <%if (check == 4 || check == 1) { %>
  <div class="bas">
     <button class="btn-follow"><%=action %></button><br/>
  
  	<table class="table">
  		<tr>
  			<td>Prénom:</td>
  			<td><%=firstname%></td>
  		</tr>
  		<tr>
  			<td>Nom</td>
  			<td><%=lastname %></td>
  		</tr>
  		<tr>
  			<td>E-mail</td>
  			<td><%=usermail %></td>
  		<tr>
  			<td>Téléphone</td>
  			<td><%=userphone %></td>
  		</tr>
  		<tr>
  			<td>Adresse</td>
  			<td><%=useraddress %></td>
  		</tr>
  	</table>
  	</div>
  	<%}  if(check == 3)  {%>
			<div class="bas">
			   <button class="btn-follow">Pending</button><br/>
			    <i class="fa fa-lock"></i>
			    <br/>
			    <h4 class="profile-status">This profile is hidden</h4>
			  </div><%} %>


</div>
<%@include file="footer.html" %>
</body>
</html>