<%--
  Created by IntelliJ IDEA.
  User: depal
  Date: 29/01/2022
  Time: 17:29
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
            <c:forEach items="${listProdotti}" var="prodotto">
                <div>
                    <a href="prodotto-visualizza?id=${prodotto.id}"><img src="images/prodotti/${prodotto.id}.jpg"></a>
                    <div id="inner_div">
                        <h2>${prodotto.nome}</h2>
                        <h3>Prezzo: ${prodotto.getPrezzo_Euro()} &euro;</h3>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:if test="${empty listProdotti}">
        <div class="error_display">
            <h1>NESSUN PRODOTTO DA VISUALIZZARE.</h1>
        </div>
    </c:if>
</main>
<%@include file="footer.jsp"%>


