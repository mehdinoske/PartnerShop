<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" --%>
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
</main>
<%@include file="footer.jsp"%>