<%--
  Created by IntelliJ IDEA.
  User: galax
  Date: 21/01/2022
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
    <jsp:param name="cssCarrello" value='<link rel="stylesheet" type="text/css" href="css/carrello_style.css">'/>
</jsp:include>
<main>
    <div class="carrello_container">
        <div class="carrello_layout">
            <c:forEach items="${Carrello.getProdotti()}" var="prodotto">
                <div>
                    <a href="prodotto-visualizza?id=${prodotto.id}"><img src="images/prodotti/${prodotto.id}.jpg"></a>
                    <div id="inner_div">
                        <h2>${prodotto.nome}</h2>
                        <h3>Prezzo: ${prodotto.getPrezzo_Euro()} &euro;</h3>
                        <h3>Quantita: ${Carrello.getQuant(prodotto.id)}</h3>
                        <form action="Carrello" method="post">
                            <input type="hidden" name="idProdotto" value="${prodotto.id}">
                            <input type="hidden" name="setQuant" value="0">
                            <input type="submit" value="Rimuovi">
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:if test="${empty Carrello.getProdotti()}">
        <div class="error_display">
            <h1>CARRELLO VUOTO.</h1>
        </div>
    </c:if>
    <c:if test="${not empty Carrello.getProdotti()}">
        <div class="prezzoFinale"><h1>Totale: ${Carrello.sommaTot()}&euro; </h1>
            <form action="Acquista" method="post">

                <input type="submit" value="CompletaAcquisto">
            </form>
        </div>
    </c:if>
</main>
<%@include file="footer.jsp"%>

