<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Adicionar tarefas</title>
</head>
<body>
   <h3>Adicionar tarefas</h3>
   <form action="adicionaTarefa" method="post">
       Descrição: <br />
       <textarea name="descricao" rows="5" cols="100"></textarea><br />
       <input type="submit" value="Adicionar">
   </form>
</body>
</html>