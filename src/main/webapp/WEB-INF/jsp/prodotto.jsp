<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<main>

            <div>
            <img src="images/prodotti/${prodotto.id}.jpg">

                <h5>${prodotto.descrizione}</h5>

                <form action="Prodotto_modifica" method="get">
                    <input type="hidden" name="id" value="${prodotto.id}">
                    <input type="submit" value="Modifica">
                </form>

                <form action="Prodotto_rimuovi" method="get">
                    <input type="hidden" name="id" value="${prodotto.id}">
                    <input type="submit" value="Rimuovi">
                </form>
            </div>

            <div>
                <h2>${prodotto.nome}</h2>
                <h4>${prodotto.categoria}</h4>
                <h4>Prezzo: ${prodotto.prezzo_Euro} &euro;</h4>
            </div>

            <div>

                <form action="Carrello" method="get">
                    <label>Quantità:</label>
                    <select name="addNum">
                        <c:forEach begin="1" end="30" varStatus="loop">
                            <option value="${loop.index}">${loop.index}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="prodId" value="${prodotto.id}">
                    <input type="submit" value="Aggiungi al carrello">
                </form>

            </div>

</main>


<%@include file="footer.jsp"%>