<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <%String alert = "" ;
    if(request.getParameter("status") != null)
    	alert = "onload=\"myFunction();\""; %>
<!DOCTYPE html>  
<html>
<head>
<meta charset="utf-8">
<title>Let's - Accueil</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300" rel="stylesheet">	
<link rel="stylesheet" href="css/signin.css">
<style>#option { outline: none;
    border: none;
    line-height: 1;
    font-weight: 600;
    font-size: 1.1rem;
    color: #333;
	font-family: "Poppins", sans-serif }</style>

</head>
<body <%=alert %>>

<div class="container"> 
      <div class="forms-container"> 
       <div class="signin-signup"> 
         <form action="Signin" method="POST" class="sign-in-form"> 
           <h2 class="title">Connexion</h2> 
           <div class="input-field"> 
             <i class="fas fa-user"></i> 
             <input type="text" name="username" autofocus placeholder="Nom d'utilisateur" minlength="8" maxlength="20" required  />
           </div> 
           <div class="input-field"> 
             <i class="fas fa-lock"></i> 
             <input type="password" name="password" placeholder="Mot de passe" minlength="8" maxlength="20" required/> 
           </div> 
           <input type="submit" value="Login" class="btn solid" /> 
           <p class="social-text">Nous sommes disponibles sur: </p>  
           <div class="social-media"> 
             <a href="#" class="social-icon"> 
               <i class="fab fa-facebook-f"></i> 
             </a> 
             <a href="#" class="social-icon"> 
               <i class="fab fa-twitter"></i> 
             </a> 
             <a href="#" class="social-icon"> 
               <i class="fab fa-google"></i> 
             </a> 
             <a href="#" class="social-icon"> 
               <i class="fab fa-linkedin-in"></i> 
             </a> 
           </div> 
          </form> 
          <form action="Signup" method="POST" class="sign-up-form"> 
            <h2 class="title">Inscription</h2> 
            <div class="input-field"> 
              <i class="fas fa-user"></i> 
              <input type="text" name="username" placeholder="Nom d'utilisateur" minlength="8" maxlength="20" required autofocus /> 
            </div> 
            <div class="input-field"> 
              <i class="fas fa-lock"></i> 
              <input type="password" name="password" placeholder="Mot de passe" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$" 
               oninvalid="this.setCustomValidity('Au moins: Longeur 8, 1 lettre majuscule, 1 lettre miniscule, 1 symbole)')"
 			   oninput="this.setCustomValidity('')"required /> 
            </div>
            <div class="input-field"> 
              <i class="fas fa-envelope"></i> 
              <input type="email" name="email" placeholder="Adresse mail" required/> 
            </div> 
            <div class="input-field"> 
              <i class="fas fa-user"></i> 
              <input type="text" name="firstname" placeholder="Votre prénom" required /> 
            </div> 
               <div class="input-field"> 
              <i class="fas fa-user"></i> 
              <input type="text" name="lastname" placeholder="Votre nom" /> 
            </div> 
               <div class="input-field"> 
              <i class="fas fa-phone"></i> 
              <input type="tel" name="phone" placeholder="Numéro de téléphone 8 chiffres" minlength="8" maxlength="8" /> 
            </div> 
               <div class="input-field"> 
              <i class="fas fa-location-arrow"></i> 
              <input type="text" name="address" placeholder="Votre adresse" /> 
            </div> 
               <div class="input-field"> 
              <i class="fas fa-venus-mars"></i> 
              <select name="gender" style="background-color: transparent ;  border: 0px">
               <option id="option">Votre sexe: </option>
               <option id="option" value="img/man.webp">Homme</option>
               <option id="option" value="img/woman.webp">Femme</option>
              </select> 
            </div> 
                           
            <input type="submit" class="btn" value="Terminer" /> 
            <p class="social-text">Nous sommes disponibles sur:</p> 
            <div class="social-media"> 
              <a href="#" class="social-icon"> 
                <i class="fab fa-facebook-f"></i> 
            </a> 
            <a href="#" class="social-icon"> 
              <i class="fab fa-twitter"></i> 
            </a> 
            <a href="#" class="social-icon"> 
              <i class="fab fa-google"></i> 
            </a> 
            <a href="#" class="social-icon"> 
              <i class="fab fa-linkedin-in"></i> 
            </a> 
          </div> 
        </form> 
      </div> 
    </div> 
    
    <div class="panels-container"> 
      <div class="panel left-panel"> 
        <div class="content"> 
          <h3>Pas encore inscrit ?</h3> 
          <p> 
             Créer votre compte en quelques secondes !<br>
              Cliquer sur "Inscription" pour joindre notre plateform et plannifier vos evenements avec vos proches.
          </p> 
          <button class="btn transparent" id="sign-up-btn"> Inscription 
          </button> 
        </div> 
      </div> 
      <div class="panel right-panel"> 
      <div class="content"> 
        <h3>Déjà inscrit ?</h3> 
        <p> 
           Nous sommes heureux de votre retour ! <br>
           Rediriger vous vers la page de connexion.
        </p> 
        <button class="btn transparent" id="sign-in-btn"> Connexion 
        </button> 
      </div> 
    </div> 
  </div> 
</div> 
 
 
  <script src="js/signin.js"></script>  
</body>
</html>