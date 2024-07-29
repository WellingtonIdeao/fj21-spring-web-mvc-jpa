package br.com.ideao.fj21tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TarefasController {

    @RequestMapping("novaTarefa")
    public String form() {
        return "tarefa/formulario";
    }
}
