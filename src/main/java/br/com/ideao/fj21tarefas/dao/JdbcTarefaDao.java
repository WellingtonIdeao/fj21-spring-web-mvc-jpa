package br.com.ideao.fj21tarefas.dao;

import br.com.ideao.fj21tarefas.model.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        try(PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM tarefa")) {
            pstmt.execute();
            transformaResultEmTarefa(pstmt, tarefas);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tarefas;
    }

    private void transformaResultEmTarefa(PreparedStatement pstmt, List<Tarefa> tarefas) {
        try(ResultSet rs = pstmt.getResultSet()) {
            while(rs.next()) {
                Calendar dataFinalizacao = Calendar.getInstance();
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getLong("id"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setFinalizado(rs.getBoolean("finalizado"));
                Date date = rs.getDate("dataFinalizacao");
                if(date != null){
                    dataFinalizacao.setTime(date);
                    tarefa.setDataFinalizacao(dataFinalizacao);
                }
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remover(Tarefa tarefa) {
        try(PreparedStatement pstmt = connection.prepareStatement("DELETE FROM tarefa WHERE id=?")) {
            pstmt.setLong(1, tarefa.getId());
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tarefa buscaPorId(long id) {
        Tarefa tarefa = new Tarefa();
        Calendar dataFinalizacao = Calendar.getInstance();
        try(PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM tarefa WHERE id=?")){
            pstmt.setLong(1, id);

            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    tarefa.setId(rs.getLong("id"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setFinalizado(rs.getBoolean("finalizado"));
                    Date date = rs.getDate("dataFinalizacao");

                    if(date != null){
                        dataFinalizacao.setTime(date);
                        tarefa.setDataFinalizacao(dataFinalizacao);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tarefa;
    }

    public void alterar(Tarefa tarefa) {
        String sql = "UPDATE tarefa SET descricao=?, finalizado=?, dataFinalizacao=? WHERE id=?";

        try(PreparedStatement pstmt= connection.prepareStatement(sql)) {
            pstmt.setString(1, tarefa.getDescricao());
            pstmt.setBoolean(2, tarefa.isFinalizado());
            Calendar dataFinalizacao = tarefa.getDataFinalizacao() ;

            if(tarefa.getDataFinalizacao() != null){
               pstmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
            } else {
                pstmt.setDate(3, null);
            }
            pstmt.setLong(4, tarefa.getId());

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
