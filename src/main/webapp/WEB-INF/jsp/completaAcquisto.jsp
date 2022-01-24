<%--
  Created by IntelliJ IDEA.
  User: galax
  Date: 22/01/2022
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Indirizzo e Pagamento"/>
</jsp:include>

<main>
    <div class="form_container_registrazione">
        <div class="inner_form_container_registrazione">
            <form action="CompletaAcquisto" method="post">

                <label for="nome">Nome (Solo lettere e spazi)</label>
                <input type="text" name="nome" id="nome" value="${utente.nome}"/>
                <label for="cognome">Cognome (Solo lettere e spazi)</label>
                <input type="text" name="cognome" id="cognome" value="${utente.cognome}"/>
                <label for="indir">Indirizzo</label>
                <input type="text" name="indirizzo" id="indir" value=""/>
                <label for="cartadc">Carta di credito</label>
                <input type="text" name="cartadc" id="cartadc" value=""/>

                <input type="submit" value="Completa Acquisto">

            </form>
        </div>
    </div>
</main>

<%@include file="footer.jsp"%>
