<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>PartnerShop - ${param.pageTitle}</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="css/homepage_style.css" type="text/css"/>
    <link rel="stylesheet" href="css/login_register_users.css" type="text/css"/>
    <link rel="stylesheet" href="css/other_pages_style.css" type="text/css"/>
    <link rel="stylesheet" href="css/utente.css" type="text/css">
    ${param.cssCarrello}
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="scripts.js"></script>
</head>
<body>
<header>
    <a href="."><img class="logo" src="images/logo_transparent.png"></a>
    <nav>
        <ul class="ul_nav">
            <li>
                <form action="ricerca" method="get">
                    <input type="text" id="searchTop" name="p" placeholder="Ricerca..." onkeyup="ricerca(this.value)"
                           value="${param.p}" list="datalist"/>
                    <datalist id="datalist">
                    </datalist>

                </form>
            </li>




            <li>
                <c:if test="${utente == null && admin ==null}">
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
           <a href="ListaDesideri">Visualizza lista desideri</a>
           <a href="VisualizzaDatiUtente">Visualizza Dati utente</a>
           <a href="OrdiniCliente">Visualizza Ordini</a>
           <a href="AggiungiSegnalazione">Invia Segnalazione</a>
           <a href="Autenticazione" method="get">Logout</a>
       </div>
   </div>
</c:if>
                <c:if test="${utente != null && utente.tipo == 1}">
                <div class="dropdown">
                     <button>${utente.nome}</button>
                      <div class="dropdown-content">
                          <a href="VisualizzaUtente">Anagrafica</a>
                        <a href="OrdiniVenditore">Visualizza Ordini</a>
                          <a href="prodotto-aggiungi-form">Aggiungi Prodotti</a>
                          <a href="visualizza-prodotti">Visualizza Prodotti</a>
                      <a href="Autenticazione" methods="get">LOGOUT</a>
                </div>
                </div>
                    </c:if>
                <c:if test="${utente == null && admin != null}">
                    <div class="dropdown">
                        <button>${admin.username}</button>
                        <div class="dropdown-content">
                            <a href="VisualizzaSegnalazioni">Visualizza lista segnalazioni</a>
                            <a href="VisualizzaUtenti">Visualizza utenti registrati</a>
                            <a href="visualizza-prodotti">Visualizza tutti i prodotti</a>
                            <a href="Autenticazione" methods="get">LOGOUT</a>
                        </div>
                    </div>
                </c:if>
</li>


            <c:if test="${utente.tipo!=1 && admin == null}">
<li><a href="Carrello"><img src="css/shopping-cart.svg"></a></li>
            </c:if>
            <li><form action="visualizza-categoria" id="form_categorie" method="get">
                <select name="categoria" id="categorie_choice">
                    <option  value=""disabled selected>Scegli categoria</option>
                    <option value="elettronica">elettronica</option>
                    <option value="abbigliamento">abbigliamento</option>
                    <option value="cancelleria">cancelleria</option>
                    <option value="utensili">utensili</option>
                    <option value="cucina">cucina</option>
                </select>
            </form></li>

</ul>
</nav>
</header>


<div class="form_container_login" id="form_login" style="display: none">
            <div class="inner_form_container_login">
         <form action="Autenticazione" method="post">
             <input type="text" name="usernameLogin" placeholder="Enter username" required>
             <input class="inner_form_container_login_submit" type="password" name="passwordLogin" placeholder="Enter password" required>
             <input type="submit" value="Login">
        </form>
</div>
</div>