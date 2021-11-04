<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <%  String address = (String) session.getAttribute("address");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Crée un événement</title>
	<%@include file="ressource.html" %>
	<link rel="stylesheet" href="css/createEvent.css">
</head>
<body>	
	<%@include file="header.html" %>
	
	<main>
	<header>
        <h1 id="title">Créer un nouveau événement </h1>
        <p id="description"> Les bons moments doivent être partagés, organiser et inviter vos proches !</p>
	</header>
	<div class="survey-parent">     
    <form action="AddEvent" method="POST" id="survey-form">
        <div class="form">
            <label id="eventTitle-label" for="eventTitle">Titre</label>
            <input type="text" name="title" id="eventTitle" placeholder="Donner un titre a votre evenement" required>
        </div>
        <div class="form">
            <label id="address-label" for="address">Adresse</label>
            <input type="text" name="address" id="address" placeholder="Préciser l'adresse" value="<%=address %>" required>
        </div>
        <div class="form">
            <label id="date-label" for="date">Date & Temps</label>
            <input type="datetime-local"   min="2020-06-07T00:00"  name="date" id="date"  placeholder="Selectionner une date et temps" required>
        </div>
        <div class="form">
            <p class="paragraph">Type de l'évévnement</p>
            <select id="dropdown" name="eventType" required>
                <option disabled selected value>Selectionner un type</option>
                <option value="Chezsoi">Chez soi</option>
                <option value="Sortie">Sortie</option>
            </select>
        </div>
        <div class="form">
        	            <label id="address">Liste des invités</label>
                               <div class="form input-group"> 
                                      <div id="dynamic_field">
                                         <input id="eventTitle"  type="text" name="guest[]" placeholder="Choisisser un invité" class="name_list" />
                                      </div>
                                         <button type="button" name="add" id="add" class="btn btn-info ">Ajouter</button>
                               </div>  
         </div>  
        <div class="form">
            <p class="paragraph">Description ou commentaire:</p>
            <textarea id="suggestions" name="description" placeholder="Description ou commentaire ici..."></textarea>
        </div>
        <div class="form">
            <button type="submit" id="submit">Inviter</button>
        </div>
    </form>
</div>   
</main>   
 <%@include file="footer.html" %>
</body>
</html>