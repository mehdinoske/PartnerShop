<%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 22/01/2022
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Invia Segnalazione"/>
</jsp:include>

<main>
    <h1>Dettagli segnalazione</h1>
    <div class="visualizza_dati_container">
        <div class="inner_div_dati">
            <h3>Segnalazione n.${segnalazione.id}</h3><h4> </h4>
            <h3>Effettuata da: </h3> <h4>${segnalazione.email}</h4>
            <h3>Motivazione: </h3> <h4>${segnalazione.motivazione}</h4>
            <h3>Commenti aggiuntivi: </h3> <h4>${segnalazione.commento}</h4>
        </div>
</div>
    <div class="inner_div_dati_submits">
        <form method="get" action="chiudiSegnalazione">
            <input type="hidden" value=${segnalazione.id} name="id">
            <input type="submit" value="chiudi segnalazione" >
        </form>
    </div>
</main>

<%@include file="footer.jsp"%>
