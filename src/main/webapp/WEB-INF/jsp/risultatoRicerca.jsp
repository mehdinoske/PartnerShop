<%--
  Created by IntelliJ IDEA.
  User: galax
  Date: 07/02/2022
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Ricerca"/>
    <jsp:param name="cssCarrello" value='<link rel="stylesheet" type="text/css" href="css/carrello_style.css">'/>
</jsp:include>
<main>
<div class="grid_container">

    <div class="grid_layout">
        <c:forEach items="${prodottiRicerca}" var="prodotto">
            <div><a href="prodotto-visualizza?id=${prodotto.id}"><img src="file/${prodotto.id}.jpg"></a>
                <form action="Carrello" method="post">
                    <input type="hidden" name="idProdotto" value="${prodotto.id}">
                    <input type="hidden" name="quant" value="1">
                    <input type="submit" value="${prodotto.prezzo_Euro} &euro;">
                </form></div>
        </c:forEach>
    </div>
</div>
    <c:if test="${empty prodottiRicerca}">
        <div class="error_display">
            <h1>NESSUN RISULTATO PER LA RICERCA.</h1>
        </div>
    </c:if>
</main>
<%@include file="footer.jsp"%>
