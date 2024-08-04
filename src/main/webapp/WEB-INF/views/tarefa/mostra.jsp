<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Alterar tarefa</title>
</head>
<body>
    <h3>Alterar tarefa - ${tarefa.id}</h3>
    <form action="alteraTarefa" method="post">
        <input type="hidden" name="id" value="${tarefa.id}" />
        Descrição:<br />
        <textarea name="descricao" rows="5" cols="100">${tarefa.descricao}</textarea><br />
        Finalizado?<input type="checkbox" name="finalizado" value="true" "${tarefa.finalizado? 'checked' : ''}"/><br/>
        <input type="text" name="dataFinalizacao"
        value="<fmt:formatDate value='${tarefa.dataFinalizacao.time}' pattern='dd/MM/yyyy' />"/> <br/>
        <input type="submit" value="Alterar">
      </form>
</body>
</html>