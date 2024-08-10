package br.com.ideao.fj21tarefas.jpa;

import br.com.ideao.fj21tarefas.model.Tarefa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class BuscarTarefas {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("tarefas");
        EntityManager manager = factory.createEntityManager();

        Query query = manager.
                createQuery("SELECT t FROM Tarefa as t WHERE t.finalizado = :paramFinalizado");
        query.setParameter("paramFinalizado", true);

        List<Tarefa> lista = query.getResultList();

        for(Tarefa tarefa: lista) {
            System.out.println(tarefa.getDescricao());
        }

        manager.close();
    }
}
