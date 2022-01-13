<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" --%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<main>
<h1><%= "Ciao Mondo!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</main>
<%@include file="footer.jsp"%>