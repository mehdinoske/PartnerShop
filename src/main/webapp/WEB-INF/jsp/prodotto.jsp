<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<main>
            <div>
                <div class="img_pr">
                <img src="images/prodotti/${prodotto.id}.jpg">
                <h5>${prodotto.descrizione}</h5>
                </div>
            </div>

            <div>
                <div>
                    <div>
                        <c:if test="${utente.tipo == 1}">
                            <form action="prodotto-modifica-form" method="post">
                                <input type="hidden" name="id" value="${prodotto.id}">
                                <input type="submit" value="Modifica">
                            </form>
                        </c:if>
                    </div>

                    <div>
                        <c:if test="${utente.tipo == 1 || admin != null}">
                            <form action="prodotto-rimuovi" method="post">
                                <input type="hidden" name="id" value="${prodotto.id}">
                                <input type="submit" value="Rimuovi">
                            </form>
                        </c:if>
                    </div>
                </div>

                <div>
                    <h2>${prodotto.nome}</h2>
                    <h4>${prodotto.categoria}</h4>
                    <h4>Prezzo: ${prodotto.prezzo_Euro} &euro;</h4>

                    <form action="Carrello" method="get">
                        <label>Quantità:</label>
                        <select name="addNum">
                            <c:forEach begin="0" end="${prodotto.disponibilita}" varStatus="loop">
                                <option value="${loop.index}">${loop.index}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="prodId" value="${prodotto.id}">
                        <input type="submit" value="Aggiungi al carrello">
                    </form>
                </div>
            </div>
</main>


<%@include file="footer.jsp"%>