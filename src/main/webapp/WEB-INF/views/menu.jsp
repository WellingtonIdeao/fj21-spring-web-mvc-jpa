<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Página Inicial</title>
</head>
<body>
    <h2>Página inicial da Lista de Tarefas</h2>
        <p>Bem vindo, ${usuarioLogado.login}</p>
        <p><a href="logout">Sair do sistema</a></p>
        <a href="listaTarefas">Clique aqui</a> para acessar a lista de tarefas
</body>
</html>