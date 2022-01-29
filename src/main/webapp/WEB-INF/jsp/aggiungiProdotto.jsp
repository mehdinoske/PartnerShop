<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Aggiungi Prodotto"/>
</jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>
    <div class="form_container_registrazione">
        <div class="inner_form_container_registrazione">
            <form action="prodotto-aggiungi" method="post">
                <label for="nome">Nome</label>
                <input type="text" name="nome" id="nome" value="${prodotto.nome}"/>
                <label for="descrizione">Descrizione</label>
                <input type="text" name="descrizione" id="descrizione" value="${prodotto.descrizione}"/>
                <label for="categoria">Categoria</label>
                <input type="text" name="categoria" id="categoria" value="${prodotto.categoria}"/>
                <label for="prezzo_Cent">Prezzo in centesimi(senza virgole e punti)</label>
                <input type="text" name="prezzo_Cent" id="prezzo_Cent" value="${prodotto.prezzo_Cent}"/>
                <label for="disponibilita">Disponibilita(senza virgole e punti)</label>
                <input type="text" name="disponibilita" id="disponibilita" value="${prodotto.disponibilita}"/>

                <input type="submit" value="Aggiungi prodotto">
            </form>
        </div>
    </div>
</main>

<%@include file="footer.jsp"%>