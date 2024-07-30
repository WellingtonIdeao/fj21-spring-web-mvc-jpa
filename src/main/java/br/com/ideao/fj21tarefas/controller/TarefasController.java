package br.com.ideao.fj21tarefas.controller;

import br.com.ideao.fj21tarefas.dao.JdbcTarefaDao;
import br.com.ideao.fj21tarefas.jdbc.ConnectionFactory;
import br.com.ideao.fj21tarefas.model.Tarefa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class TarefasController {

    @RequestMapping("novaTarefa")
    public String form() {
        return "tarefa/formulario";
    }

    @RequestMapping("adicionaTarefa")
    public String adiciona(Tarefa tarefa) {
        try(Connection connection = new ConnectionFactory().getConnection()) {
            JdbcTarefaDao dao = new JdbcTarefaDao(connection);
            dao.adicionar(tarefa);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "tarefa/adicionada";
    }
}
