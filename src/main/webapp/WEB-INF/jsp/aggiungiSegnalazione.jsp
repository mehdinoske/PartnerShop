<%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 20/01/2022
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Invia Segnalazione"/>
</jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>


    <div class="form_segnalazione_container">
        <div class="inner_form_segnalazione">
            <form action="AggiungiSegnalazione" method="post">
            <select name="motivazione" id="segnalazioni">
                <option disabled selected>Scegli motivazione</option>
                <option value="mot1">Venditore poco serio</option>
                <option value="mot2">mot2</option>
                <option value="mot3">mot3</option>
                <option value="mot4">mot4</option>
            </select>
            <textarea class="text" name="commentiAggiuntivi"  id="commentiAggiuntivi" placeholder="inserisci dei commenti aggiuntivi..." ></textarea>
                <input type="submit" value="Invia Segnalazione">
            </form>
        </div>
    </div>


</main>
<%@include file="footer.jsp"%>