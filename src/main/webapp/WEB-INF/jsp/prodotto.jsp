<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${prodotto.nome}"/>
</jsp:include>
<main>
    <div class="grid_container">

        <div class="visualizza_prodotto">
            <div class="image_prodotto"><img src="file/${prodotto.id}.jpg"></div>
            <div class="div_descrizione"><div id="titolo">${prodotto.nome}</div> <h5>${prodotto.descrizione}</h5> <div id="prezzo">${prodotto.getPrezzo_Euro()} &euro;</div>
                <c:if test="${utente.tipo == 0 && admin == null}">
               <a href="AggiungiListaDesideri?idProdotto=${prodotto.id}">
                   <div class="preferitiButton">
                    <span class="material-icons">
                        bookmark_add
                    </span>
                   </div>
               </a>
                </c:if>
            </div>
            <div class="form_container_prodotto">
            <c:if test="${utente.tipo != 1 && admin == null}">
                <form action="Carrello" method="get">
                    <select name="quant" style="width:200px">
                        <option disabled selected>Seleziona Quantità</option>
                        <c:forEach begin="1" end="${prodotto.disponibilita}" varStatus="loop">
                            <option value="${loop.index}">${loop.index}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="idProdotto" value="${prodotto.id}">
                    <input type="submit" value="Aggiungi al carrello">
                </form>
            </c:if>
                <c:if test="${utente.tipo == 1}">
                    <form action="prodotto-modifica-form" method="post">
                        <input type="hidden" name="id" value="${prodotto.id}">
                        <input type="submit" value="Modifica">
                    </form>
                </c:if>

                <c:if test="${utente.tipo == 1 || admin != null}">
                    <form action="prodotto-rimuovi" method="post">
                        <input type="hidden" name="id" value="${prodotto.id}">
                        <input type="submit" value="Rimuovi">
                    </form>
                </c:if>
            </div>
            <div class="info_container">

                <h4><a href="visualizza-categoria?categoria=${prodotto.categoria}">${prodotto.categoria}</a></h4>

            </div>
        </div>

    </div>



</main>


<%@include file="footer.jsp"%>