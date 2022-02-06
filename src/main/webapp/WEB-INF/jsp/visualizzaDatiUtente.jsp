<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 20/01/22
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Il mio profilo"/>
</jsp:include>

<main>
    <h1> Benvenuto ${utente.nome}</h1>
    <div class="visualizza_dati_container">
        <div class="inner_div_dati">
            <h3>Nome e cognome: </h3> <h4>${utente.nome} ${utente.cognome}</h4>
            <h3>Username: </h3> <h4>${utente.username}</h4>
            <h3>E-mail: </h3> <h4>${utente.email}</h4>
            <h3>Data di nascita: </h3> <h4> ${utente.ddn}</h4>
            <h3>Indirizzo: </h3> <h4> ${utente.indirizzo}</h4>
            <h3>Cellulare: </h3> <h4> ${utente.cellulare}</h4>
        </div>
        <div class="inner_div_dati_submits">
            <form action="VisualizzaModifica">
                <input type="submit" value="Modifica Dati">
            </form>

            <form action="CancellaDatiUtenti">
                <input type="text" name="email" id="email" value="${utente.email}" hidden/>
                <input type="submit" value="Cancella Dati">
            </form>
        </div>
    </div>



</main>
<%@include file="footer.jsp"%>
