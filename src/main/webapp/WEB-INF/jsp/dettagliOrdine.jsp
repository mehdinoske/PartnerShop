<%--
  Created by IntelliJ IDEA.
  User: galax
  Date: 24/01/2022
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Ordini Effettuati"/>
    <jsp:param name="cssCarrello" value='<link rel="stylesheet" type="text/css" href="css/carrello_style.css">'/>
</jsp:include>
<main>
    <div class="dettagli_ordine">
        <div class="dettagli_layout">
            <div>
                <div>
                <h2>ORDINE #${ordine.id}</h2>
                <h3>Effettuato da: ${ordine.getEmailCliente()}</h3>
                <h3>Data ordine: ${ordine.getDataSql()}</h3>
                <h3>Indirizzo: ${ordine.getIndirizzo()}</h3>
                <h3>Prezzo totale: ${ordine.getPrezzo_tot()}</h3>
                <h3>N. prodotti: ${ordine.getProdotti().size()}</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="carrello_container">
        <div class="carrello_layout">
            <c:forEach items="${ordine.getProdotti()}" var="prodotto">
                <div>
                        <a href="prodotto-visualizza?id=${prodotto.id}"><img src="images/prodotti/${prodotto.id}.jpg"></a>
                    <div id="inner_div">
                            <h2>${prodotto.nome}</h2>
                            <h3>Prezzo: ${prodotto.prezzo_Cent} &euro;</h3>
                            <h3>Quantita: ${prodotto.disponibilita}</h3>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:if test="${empty ordini}">
        <div class="error_display">
            <h1>NESSUN ORDINE.</h1>
        </div>
    </c:if>
</main>
<%@include file="footer.jsp"%>
