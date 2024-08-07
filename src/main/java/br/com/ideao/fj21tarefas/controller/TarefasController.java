package br.com.ideao.fj21tarefas.controller;

import br.com.ideao.fj21tarefas.dao.JdbcTarefaDao;
import br.com.ideao.fj21tarefas.model.Tarefa;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TarefasController {
    private JdbcTarefaDao dao;

    @Autowired
    public TarefasController(JdbcTarefaDao dao) {
        this.dao = dao;
    }

    @RequestMapping("novaTarefa")
    public String form() {
        return "tarefa/formulario";
    }

    @RequestMapping("adicionaTarefa")
    public String adiciona(@Valid Tarefa tarefa, BindingResult result) {

        if(result.hasFieldErrors("descricao")) {
            return "tarefa/formulario";
        }
        dao.adicionar(tarefa);
        return "tarefa/adicionada";
    }

    @RequestMapping("listaTarefas")
    public String lista(Model model) {
        model.addAttribute("tarefas", dao.listar());
        return "tarefa/lista";
    }

    @RequestMapping("removeTarefa")
    public String remove(Tarefa tarefa) {
        dao.remover(tarefa);
        return "redirect:listaTarefas";
//        return "forward:listaTarefas";
    }

    @RequestMapping("mostraTarefa")
    public String mostra(long id, Model model) {
        model.addAttribute("tarefa", dao.buscaPorId(id));
        return "tarefa/mostra";
    }

    @RequestMapping("alteraTarefa")
    public String altera(Tarefa tarefa) {
        dao.alterar(tarefa);
        return "redirect:listaTarefas";
    }

    @ResponseBody
    @RequestMapping("finalizaTarefa")
    public void finaliza(long id) {
        dao.finalizar(id);
    }

    @ResponseBody
    @RequestMapping("remove")
    public void removeAgora(long id) {
        dao.removerAjax(id);
    }
}
