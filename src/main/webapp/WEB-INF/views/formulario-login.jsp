<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Página de Login das Tarefas</h2>
    <form action="efetuaLogin" method="post">
        Login: <input type="text" name="login" /> <br />
        Senha: <input type="password" name="senha" /> <br />
        <input type="submit" value="Entrar nas tarefas" />
    </form>
</body>
</html>