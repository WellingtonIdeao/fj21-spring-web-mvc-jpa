package br.com.ideao.fj21tarefas.dao;

import br.com.ideao.fj21tarefas.model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTarefaDao {
    private Connection connection;

    public JdbcTarefaDao(Connection connection) {
        this.connection = connection;
    }

    public void adicionar(Tarefa tarefa) {
        String sql = "INSERT INTO tarefa " +
                "(descricao, finalizado) " +
                "VALUES (?, ?)";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tarefa.getDescricao());
            pstmt.setBoolean(2, tarefa.isFinalizado());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
