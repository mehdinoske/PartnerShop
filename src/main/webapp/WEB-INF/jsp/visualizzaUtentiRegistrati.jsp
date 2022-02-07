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
    <section>
        <table>
            <thead>
            <tr>
                <th>Nome</th>
                <th>Cognome</th>
                <th>E-mail</th>
                <th>Username</th>
                <!--<th>  </th>-->
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${utenti}" var="utente">
                <tr>
                    <td>${utente.nome}</td>
                    <td>${utente.cognome}</td>
                    <td>${utente.email}</td>
                    <td>${utente.username}</td>
                    <!-- <td><a href="CancellaDatiUtenti?email" >Rimuovi</a></td> -->
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</main>
<%@include file="footer.jsp"%>
