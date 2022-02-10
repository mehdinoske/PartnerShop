<%--
  Created by IntelliJ IDEA.
  User: galax
  Date: 10/02/2022
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="La mia lista desideri"/>
    <jsp:param name="cssCarrello" value='<link rel="stylesheet" type="text/css" href="css/carrello_style.css">'/>
</jsp:include>
<main>
    <div class="carrello_container">
        <div class="carrello_layout">
            <c:forEach items="${cliente.listaDesideri}" var="prodotto">
                <div>
                    <a href="prodotto-visualizza?id=${prodotto.id}"><img src="images/prodotti/${prodotto.id}.jpg"></a>
                    <div id="inner_div">
                        <h2>${prodotto.nome}</h2>
                        <h3>Prezzo: ${prodotto.getPrezzo_Euro()} &euro;</h3>
                        <a href="RimuoviListaDesideri?idProdotto=${prodotto.id}"><div class="preferitiButton">
                            <span class="material-icons">
                            bookmark_remove
                        </span>
                        </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:if test="${empty cliente.listaDesideri}">
        <div class="error_display">
            <h1>LA TUA LISTA DESIDERI E' VUOTA.</h1>
        </div>
    </c:if>
</main>
<%@include file="footer.jsp"%>
