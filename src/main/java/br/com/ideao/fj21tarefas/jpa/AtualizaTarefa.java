package br.com.ideao.fj21tarefas.jpa;

import br.com.ideao.fj21tarefas.model.Tarefa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AtualizaTarefa {
    public static void main(String[] args) {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1);
        tarefa.setDescricao("Estudar JPA e Hibernate");
        tarefa.setFinalizado(false);
        tarefa.setDataFinalizacao(null);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
        manager.merge(tarefa);
        manager.getTransaction().commit();
        manager.close();
    }
}
