<%--
  Created by IntelliJ IDEA.
  User: galax
  Date: 23/01/2022
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Ordine Effettuato"/>
</jsp:include>
<main>
    <div class="error_display">
        <h1>Ordine effettuato con successo! Controlla nella sezione ordini.</h1>
    </div>
</main>
<%@include file="footer.jsp"%>
