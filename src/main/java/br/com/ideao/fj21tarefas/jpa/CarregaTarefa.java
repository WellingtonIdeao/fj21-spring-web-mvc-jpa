package br.com.ideao.fj21tarefas.jpa;

import br.com.ideao.fj21tarefas.model.Tarefa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CarregaTarefa {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("tarefas");
        EntityManager manager = factory.createEntityManager();
        Tarefa encontrada = manager.find(Tarefa.class, 1L);
        System.out.println(encontrada.getDescricao());
        manager.close();
    }
}
