<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 21/01/2022
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
        <section>
            <table>
                <thead>
                <tr>
                    <th>id</th>
                    <th>Email cliente</th>
                    <th>Motivazione</th>
                    <th>Stato</th>
                    <th>  </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${segnalazioni}" var="segnalazione">

                    <form action="dettagliSegnalazioni" method="post">
                        <tr>
                            <td>${segnalazione.id}</td>
                            <td>${segnalazione.email}</td>
                            <td>${segnalazione.motivazione}</td>
                            <td>${segnalazione.stato}</td>
                            <td><a href="visualizzaDettagliSegn?id=${segnalazione.id}" >Dettagli</a></td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </section>
</main>
<%@include file="footer.jsp"%>