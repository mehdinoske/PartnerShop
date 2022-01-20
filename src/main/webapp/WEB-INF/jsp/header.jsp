<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>PartnerShop - ${param.pageTitle}</title>
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
                <c:if test="${utente == null}">
                    <div class="dropdown">
                        <button>ACCOUNT</button>
                        <div class="dropdown-content">
                            <a href="Registrazione?id=cliente" >Registra Cliente </a>
                            <a href="Registrazione?id=venditore" >Registra Venditore  </a>
                            <a onclick="toggleForm()">LOGIN</a>
                        </div>
                    </div>
                </c:if>

<c:if test="${utente.tipo== 0}">
   <div class="dropdown">
       <button>${utente.nome}</button>
       <div class="dropdown-content">
           <a href="VisualizzaDatiUtente">Visualizza Dati utente</a>
           <a href="ListaDesideri">Visualizza lista desideri</a>
           <a href="VisualizzaOrdini">Visualizza Ordini</a>
           <a href="Segnalazione">Invia Segnalazione</a>
           <a href="Autenticazione" method="get">Logout</a>
       </div>
   </div>
</c:if>
                <c:if test="${utente != null && utente.tipo == 1}">--%>
                <div class="dropdown">
                     <button>${utente.nome}</button>
                      <div class="dropdown-content">
                        <a href="UtenteOrdini">Ordini</a>
                        <a href="Anagrafica">Anagrafica</a>
                      <a href="Autenticazione" methods="get">LOGOUT</a>
                </div>
                </div>
                    </c:if>
</li>



<li><a href="Carrello"><img src="css/shopping-cart.svg"></a></li>
</ul>
</nav>
</header>


<div class="form_container_login" id="form_login">
            <div class="inner_form_container_login">
         <form action="Autenticazione" method="post">
             <input type="text" name="usernameLogin" placeholder="Enter username" required>
             <input class="inner_form_container_login_submit" type="password" name="passwordLogin" placeholder="Enter password" required>
             <input type="submit" value="Login">
        </form>
</div>
</div>