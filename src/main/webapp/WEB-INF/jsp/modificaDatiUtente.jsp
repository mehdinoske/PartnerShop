<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 20/01/22
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Modifica dati"/>
</jsp:include>
<main>
    <div class="form_container_registrazione">
        <div class="inner_form_container_registrazione">
            <form action="Registrazione?id=cliente" method="post">
                <label for="nome">Nome (Solo lettere e spazi)</label>
                <input type="text" name="nome" id="nome" value="${utente.nome}" oninput="validaNome()"/>
                <label for="cognome">Cognome (Solo lettere e spazi)</label>
                <input type="text" name="cognome" id="cognome" value="${utente.cognome}" oninput="validaCognome()"/>
                <label for="cell">Cellulare</label>
                <input type="text" name="cellulare" id="cell" value="${utente.cellulare}" oninput="validaCellulare()"/>
                <label for="indir">Indirizzo</label>
                <input type="text" name="indirizzo" id="indir" value="${utente.indirizzo}" oninput="validaIndirizzo()"/>
                <label for="pass">Password (almeno 8 caratteri tra cui: una lettera minuscola, una lettera maiuscola, un numero)</label>
                <input type="password" name="password" id="pass" value="${utente.password}" oninput="validaPassword()"/>
                <label for="passConf">Conferma Password</label>
                <input type="password" name="passwordConferma" id="passConf" value="${utente.password}" oninput="validaPassword()"/>
                <input type="submit" value="Modifica">
            </form>
        </div>
    </div>
</main>
<%@include file="footer.jsp"%>