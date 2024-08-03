<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Adicionar tarefas</title>
</head>
<body>
   <h3>Adicionar tarefas</h3>
   <form:errors path="tarefa.descricao" cssStyle="color:red" />
   <form action="adicionaTarefa" method="post">
       Descrição: <br />
       <textarea name="descricao" rows="5" cols="100"></textarea><br />
       <input type="submit" value="Adicionar">
   </form>
</body>
</html>