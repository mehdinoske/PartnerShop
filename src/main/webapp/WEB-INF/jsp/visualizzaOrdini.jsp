<%--
  Created by IntelliJ IDEA.
  User: galax
  Date: 24/01/2022
  Time: 13:12
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
    <div class="lista_container">
        <div class="lista_layout">
            <c:forEach items="${ordini}" var="ordine">
                <div>
                    <div id="inner_div">
                        <h2>ORDINE #${ordine.id}</h2>
                        <h3>Effettuato da: ${ordine.getEmailCliente()}</h3>
                        <h3>N. prodotti: ${ordine.getProdotti().size()}</h3>
                        <form action="OrdiniCliente" method="post">
                            <input type="hidden" name="idOrdine" value="${ordine.id}">
                            <input type="submit" value="Visualizza dettagli">
                        </form>
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
