<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 05/02/22
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Tutti gli utenti"/>
</jsp:include>
<main>
    <c:if test="${not empty utenti}">
        <table>
            <tr>
                <th>NOME</th>
                <th>COGNOME</th>
                <th>USERNAME</th>
                <th>EMAIL</th>
                <th>    </th>
            </tr>
            <c:forEach items="${utenti}" var="utente">
                <tr>
                    <td>${utente.nome}</td>
                    <td>${utente.cognome}</td>
                    <td>${utente.username}</td>
                    <td>${utente.email}</td>
                    <td><a href="CancellaDatiUtenti?email=${utente.email}" >RIMUOVI</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty utenti}">
        <div class="error_display">
            <h1>NESSUN UTENTE REGISTRATO.</h1>
        </div>
    </c:if>
</main>
<%@include file="footer.jsp"%>
