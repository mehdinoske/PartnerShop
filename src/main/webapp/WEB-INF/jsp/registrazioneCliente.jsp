<%--
  Created by IntelliJ IDEA.
  User: galax
  Date: 18/01/2022
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" --%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<main>
    <div class="form_container_registrazione">
        <div class="inner_form_container_registrazione">
            <form action="Registrazione?id=cliente" method="post">
                <label for="nome">Nome (Solo lettere e spazi)</label>
                <input type="text" name="nome" id="nome" value="" oninput="validaNome()"/>
                <label for="cognome">Cognome (Solo lettere e spazi)</label>
                <input type="text" name="cognome" id="cognome" value="" oninput="validaCognome()"/>
                <input type="date" name="ddn" value="1999-12-07" hidden/>
                <label for="email">Email</label>
                <input type="text" name="email" id="email" value="" oninput="validaEmail()"/>
                <label for="username">Username (almeno 6 caratteri,solo lettere e numeri)</label>
                <input type="text" name="username" value="" id="username"/>
                <label for="cell">Cellulare</label>
                <input type="text" name="cellulare" id="cell" value="" oninput="validaCellulare()"/>
                <label for="indir">Indirizzo</label>
                <input type="text" name="indirizzo" id="indir" value="" oninput="validaIndirizzo()"/>
                <label for="pass">Password (almeno 8 caratteri tra cui: una lettera minuscola, una lettera maiuscola, un numero)</label>
                <input type="password" name="password" id="pass" value="" oninput="validaPassword()"/>
                <label for="passConf">Conferma Password</label>
                <input type="password" name="passwordConferma" id="passConf" value="" oninput="validaPassword()"/>
                <input type="submit" value="Registrami" id="registrami" disabled><span id="notificaMes"></span>

            </form>
        </div>
    </div>
</main>
<%@include file="footer.jsp"%>