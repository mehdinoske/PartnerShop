<%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 13/01/2022
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%--@ page contentType="text/html;charset=UTF-8" language="java" --%>
<html>
<head>
    <title>Login</title>
</head>
<body>
        <form id="formlogin" action="Login" method="post">
        <input type="text" name="username" placeholder="Username">
        <input type="password" name="password" placeholder="Password">
        <button class="sublogin" type="submit">Login</button>
        <h4><label>Non hai ancora un account?</label></h4>
        <a href="registrazioneForm">Registrati</a>
    </form>
</body>
</html>
