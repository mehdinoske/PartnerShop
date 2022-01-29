<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<main>

    <c:if test="${mes==1}">
        <div class="error_display" id="usNA">
            <h1>Username e password non validi.</h1>
        </div>
    </c:if>
    <div class="flip-card-container">
    <div class="flip-card">
        <div class="flip-card-inner" >
            <div class="flip-card-front">
                <img src="images/categorie/elettronica.jpg" alt="Avatar" style="width:298.4px;height:198.4px;">
            </div>
            <div class="flip-card-back">
                <a href="visualizza-categoria?categoria=elettronica">
                <h1>ELETTRONICA</h1>
                <p>Scopri il meglio della tecnologia!</p>
                </a>
            </div>
        </div>
    </div>
    <div class="flip-card">
        <div class="flip-card-inner">
            <div class="flip-card-front">
                <img src="images/categorie/abbigliamento.jpg" alt="Avatar" style="width:298.4px;height:198.4px;">
            </div>
            <div class="flip-card-back" style="background-color: lightpink">
                <a href="visualizza-categoria?categoria=abbigliamento">
                <h1>ABBIGLIAMENTO</h1>
                <p>Scopri i migliori capi del sito!</p>
                    </a>
            </div>
        </div>
    </div>
        <div class="flip-card">
            <div class="flip-card-inner">
                <div class="flip-card-front" >
                    <img src="images/categorie/cancelleria.jpg" alt="Avatar" style="width:298.4px;height:198.4px;">
                </div>
                <div class="flip-card-back" style="background-color: limegreen">
                    <a href="visualizza-categoria?categoria=cancelleria">
                    <h1>CANCELLERIA</h1>
                    <p>Scopri i migliori prodotti per l'ufficio!</p>
                        </a>
                </div>
            </div>
        </div>
        <div class="flip-card">
            <div class="flip-card-inner">
                <div class="flip-card-front">
                    <img src="images/categorie/utensili.jpg" alt="Avatar" style="width:298.4px;height:198.4px;">
                </div>
                <div class="flip-card-back" style="background-color: darkred">
                    <a href="visualizza-categoria?categoria=utensili">
                    <h1>UTENSILI</h1>
                    <p>Scopri i migliori utensili!</p>
                    </a>
                </div>
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