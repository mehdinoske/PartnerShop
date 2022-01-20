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
<link rel="stylesheet" href="css/utente.css" type="text/css">
<main>
    <h1> Benvenuto ${utente.nome}</h1>
    <div>
        <p>Nome e cognome: ${utente.nome} ${utente.cognome}</p>
        <p>Username: ${utente.username}</p>
        <p>E-mail: ${utente.email}</p>
        <p>Data di nascita: ${utente.ddn}</p>
        <p>Indirizzo: ${utente.indirizzo}</p>
        <p>Cellulare: ${utente.cellulare}</p>
    </div>

    <div>
        <form action="ModificaDatiUtenti">
            <input type="submit" value="Modifica Dati" id="modifica">
        </form>

        <form action="CancellaDatiUtenti">
            <input type="submit" value="Cancella Dati" id="cancella">
        </form>


    </div>
</main>
<%@include file="footer.jsp"%>
