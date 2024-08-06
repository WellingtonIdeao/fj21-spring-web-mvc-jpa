package br.com.ideao.fj21tarefas.dao;

import br.com.ideao.fj21tarefas.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUsuarioDao {
    private Connection connection;

    public JdbcUsuarioDao(Connection connection) {
        this.connection = connection;
    }

    public boolean existeUsuario(Usuario usuario) {
        String sql = "SELECT * FROM usuario WHERE login=? AND senha=?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1,usuario.getLogin());
            pstmt.setString(2, usuario.getSenha());
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
