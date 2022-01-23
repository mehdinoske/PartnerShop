<%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 22/01/2022
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<main>
   <h1>Dettagli segnalazione</h1><br>
    <h3>Segnalazione n.${segnalazione.id}</h3><br><br>
    <h4>Effettuata da: ${segnalazione.email}</h4><br>
    <h4>Motivazione: ${segnalazione.motivazione}</h4><br>
    <h4>Commenti aggiuntivi: ${segnalazione.commento}</h4><br>
    <form method="get" action="chiudiSegnalazione">
        <input type="hidden" value=${segnalazione.id} name="id">
        <input type="submit" value="chiudi segnalazione" >
    </form>

</main>

<%@include file="footer.jsp"%>
