package br.com.ideao.fj21tarefas.controller;

import br.com.ideao.fj21tarefas.dao.JdbcTarefaDao;
import br.com.ideao.fj21tarefas.jdbc.ConnectionFactory;
import br.com.ideao.fj21tarefas.model.Tarefa;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public String adiciona(@Valid Tarefa tarefa, BindingResult result) {

        if(result.hasFieldErrors("descricao")) {
            return "tarefa/formulario";
        }

        try(Connection connection = new ConnectionFactory().getConnection()) {
            JdbcTarefaDao dao = new JdbcTarefaDao(connection);
            dao.adicionar(tarefa);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "tarefa/adicionada";
    }
}
