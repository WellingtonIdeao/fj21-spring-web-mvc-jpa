<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de tarefas</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"></c:url>">
</head>
<body>
    <a href="novaTarefa">Criar nova tarefa</a>
    <br /> <br />
     <table>
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Descrição</th>
                    <th scope="col">Finalizado?</th>
                    <th scope="col">Data de finalização</th>
                    <th scope="col">Opções</th>
                </tr>
            </thead>

        <c:forEach var="tarefa" items="${tarefas}">
            <tbody>
                <tr>
                    <th scope="row">${tarefa.id}</th>
                    <td>${tarefa.descricao}</td>
                    <c:if test="${tarefa.finalizado eq false}">
                        <td>Não finalizado</td>
                    </c:if>
                    <c:if test="${tarefa.finalizado eq true}">
                        <td>Finalizado</td>
                    </c:if>
                    <td>
                        <fmt:formatDate value="${tarefa.dataFinalizacao.time}"
                        pattern="dd/MM/yyyy" />
                    </td>
                    <td>
                        <a href="mostraTarefa?id=${tarefa.id}">Alterar</a>
                        <a href="removeTarefa?id=${tarefa.id}">Remover</a>
                    </td>
                </tr>
            </tbody>
        </c:forEach>
            <tfoot>
                <tr>
                    <th scope="row" colspan="5"></th>
                </tr>
            </tfoot>
        </table>
</body>
</html>