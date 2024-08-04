package br.com.ideao.fj21tarefas.controller;

import br.com.ideao.fj21tarefas.dao.JdbcTarefaDao;
import br.com.ideao.fj21tarefas.jdbc.ConnectionFactory;
import br.com.ideao.fj21tarefas.model.Tarefa;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("listaTarefas")
    public String lista(Model model) {
        try(Connection connection = new ConnectionFactory().getConnection()) {
            JdbcTarefaDao dao = new JdbcTarefaDao(connection);
            model.addAttribute("tarefas", dao.listar());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "tarefa/lista";
    }

    @RequestMapping("removeTarefa")
    public String remove(Tarefa tarefa) {
        try(Connection connection = new ConnectionFactory().getConnection()) {
            JdbcTarefaDao dao = new JdbcTarefaDao(connection);
            dao.remover(tarefa);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:listaTarefas";
//        return "forward:listaTarefas";
    }

    @RequestMapping("mostraTarefa")
    public String mostra(long id, Model model) {
        try(Connection connection = new ConnectionFactory().getConnection()) {
            JdbcTarefaDao dao = new JdbcTarefaDao(connection);
            model.addAttribute("tarefa", dao.buscaPorId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "tarefa/mostra";
    }

    @RequestMapping("alteraTarefa")
    public String altera(Tarefa tarefa) {
        try(Connection connection = new ConnectionFactory().getConnection()) {
            JdbcTarefaDao dao = new JdbcTarefaDao(connection);
            dao.alterar(tarefa);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:listaTarefas";
    }
}
