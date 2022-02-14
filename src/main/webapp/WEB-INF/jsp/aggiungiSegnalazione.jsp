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
                <option value="mot1">Prodotto danneggiato</option>
                <option value="mot2">Descrizione prodotto fuorviante</option>
                <option value="mot3">Ordine non ricevuto</option>
                <option value="mot4">Ordine in ritardo</option>
            </select>
            <textarea class="text" name="commentiAggiuntivi"  id="commentiAggiuntivi" placeholder="inserisci dei commenti aggiuntivi..." oninput="validaCommentoAggiuntivo()"></textarea>
                <input type="submit" id="segnalazioneInvio" value="Invia Segnalazione" disabled>
                <span style="color: white" id="notificaMes4"></span>
            </form>

        </div>
    </div>


</main>
<%@include file="footer.jsp"%>