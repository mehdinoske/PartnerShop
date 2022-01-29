<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<main>
    <div class="flip-card">
        <div class="flip-card-inner">
            <div class="flip-card-front">
                <img src="images/categorie/elettronica.jpg" alt="Avatar" style="width:300px;height:200px;">
            </div>
            <div class="flip-card-back">
                <h1>ELETTRONICA</h1>
                <p>Scopri il meglio della tecnologia!</p>
            </div>
        </div>
    </div>
    <div class="grid_container">

        <div class="grid_layout">
            <c:forEach items="${prodotti}" var="prodotto">
                <div><a href="prodotto-visualizza?id=${prodotto.id}"><img src="images/prodotti/${prodotto.id}.jpg"></a>
                    <form action="Carrello" method="post">
                        <input type="hidden" name="idProdotto" value="${prodotto.id}">
                        <input type="hidden" name="quant" value="1">
                        <input type="submit" value="${prodotto.prezzo_Euro} &euro;">
                    </form></div>
            </c:forEach>
        </div>

    </div>
</main>

<%@include file="footer.jsp"%>