package br.com.ideao.fj21tarefas.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Calendar;

public class Tarefa {
    private long id;

    @NotBlank(message = "{tarefa.descricao.vazia}")
    @Size(min = 5, message ="{tarefa.descricao.pequena}" )
    private String descricao;

    private boolean finalizado;
    private Calendar dataFinalizacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Calendar getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Calendar dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
}
