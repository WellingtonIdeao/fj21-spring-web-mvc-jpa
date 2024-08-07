package br.com.ideao.fj21tarefas.dao;

import br.com.ideao.fj21tarefas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUsuarioDao {
    private final Connection connection;

    @Autowired
    public JdbcUsuarioDao(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
