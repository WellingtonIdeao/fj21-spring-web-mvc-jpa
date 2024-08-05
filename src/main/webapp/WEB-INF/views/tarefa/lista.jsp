<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de tarefas</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"></c:url>">
    <script  type="text/javascript" src="resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function finalizaAgora(id) {
        $.post("finalizaTarefa", {'id' : id}, function() {
            // selecionando o elemento html através da
            // ID e alterando o HTML dele
            $("#tarefa_"+id).html("Finalizado");
        });
    }
    function removeAgora(id) {
        $.post("remove", {'id': id}, function() {
            $("#tarefa_row"+id).closest("tr").hide();
        });
    }
</script>
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
                <tr id="tarefa_row${tarefa.id}">
                    <th scope="row">${tarefa.id}</th>
                    <td>${tarefa.descricao}</td>
                    <c:if test="${tarefa.finalizado eq false}">
                        <td id="tarefa_${tarefa.id}">
                            <a href="#" onClick="finalizaAgora(${tarefa.id})">Finaliza agora!</a>
                        </td>
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
                        <a href="#" onClick="removeAgora(${tarefa.id})">Remover</a>
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