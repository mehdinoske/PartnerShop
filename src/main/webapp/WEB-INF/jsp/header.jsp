<%--
  Created by IntelliJ IDEA.
  User: galax
  Date: 13/01/2022
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" --%>
<!DOCTYPE html>
<html>
<head>
    <title>PartnerShop Homepage</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet">
    <link rel="stylesheet" href="css/homepage_style.css" type="text/css"/>
    <link rel="stylesheet" href="css/login_register_users.css" type="text/css"/>
    <link rel="stylesheet" href="css/cards_style.css" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="scripts.js"></script>
</head>
<body>
<header>
    <a href="."><img class="logo" src="images/logo_transparent.png"></a>
    <nav>
        <ul class="ul_nav">
            <li>
                <form action="Ricerca" method="get">
                    <input type="text" id="searchTop" name="p" placeholder="Ricerca..." onkeyup="" value="" list="datalist">
                    <datalist id="datalist">
                    </datalist>
                </form>
            </li>
            <li><form action="VisualizzaCategoria" id="form_categorie" method="get">
                <select name="idCategoria" id="categorie_choice">
                    <option  value=""disabled selected>Scegli categoria</option>
                    <%-- <c:forEach items="${categorie}" var="categoria">
                        <option value="${categoria.idCategoria}">${categoria.nome}</option>
                    </c:forEach> --%>
                </select>
            </form></li>



            <li>
                <%--<c:if test="${utente == null}">--%>
                    <div class="dropdown">
                        <button>ACCOUNT</button>
                        <div class="dropdown-content">
                            <a href="RegistraUtente">Registra Cliente </a>
                            <a href="RegistraVenditore">Registra Venditore  </a>
                            <a href="Login">LOGIN</a>
                        </div>
                    </div>
                <%--</c:if>--%>
<%--<c:if test="${utente != null && utente.admin}">--%>
   <div class="dropdown">
       <button>${Utente.nome}</button>
       <div class="dropdown-content">
           <a href="AdminOrdini">Registra Cliente</a>
           <a href="AdminUtenti">Registra Venditore</a>
           <a href="Logout">LOGIN</a>
       </div>
   </div>
                    <%--</c:if>--%>
                    <%--<c:if test="${utente != null && utente.admin == false}">--%>
   <div class="dropdown">
       <button>${utente.nome}</button>
       <div class="dropdown-content">
           <a href="UtenteOrdini">Ordini</a>
           <a href="Anagrafica">Anagrafica</a>
           <a href="Logout" methods="post">LOGOUT</a>
       </div>
   </div>
                    <%--</c:if>--%>
</li>



<li><a href="Carrello"><img src="css/shopping-cart.svg"></a></li>
</ul>
</nav>
</header>


<div class="form_container_login" id="form_login">
            <div class="inner_form_container_login">
         <form action="Login" method="post">
             <input type="text" name="usernameLogin" placeholder="Enter username" required>
             <input type="password" name="passwordLogin" placeholder="Enter password" required>
             <input type="submit" value="Login">
        </form>
            <h5>Oppure</h5>
<form action="RegistratiForm" method="get">
<input type="submit" value="Registrati">
</form>
</div>
</div>