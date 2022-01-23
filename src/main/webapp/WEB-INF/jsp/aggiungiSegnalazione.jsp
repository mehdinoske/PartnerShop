<%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 20/01/2022
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>


<form action="AggiungiSegnalazione" method="post">
<label for="segnalazioni">Scegli una motivazione: </label>

<select name="motivazione" id="segnalazioni">
    <option value="mot1">Venditore poco serio</option>
    <option value="mot2">mot2</option>
    <option value="mot3">mot3</option>
    <option value="mot4">mot4</option>
</select><br>

    <textarea rows="4" cols="10" class="text" name="commentiAggiuntivi"  id="commentiAggiuntivi" placeholder="inserisci dei commenti aggiuntivi..." ></textarea><br>
    <input type="submit" value="Invia Segnalazione">
</form>
</main>
<%@include file="footer.jsp"%>