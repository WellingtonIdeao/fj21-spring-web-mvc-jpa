package br.com.ideao.fj21tarefas.controller;

import br.com.ideao.fj21tarefas.dao.JdbcUsuarioDao;
import br.com.ideao.fj21tarefas.jdbc.ConnectionFactory;
import br.com.ideao.fj21tarefas.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class LoginController {

    @RequestMapping("loginForm")
    public String loginForm() {
        return "formulario-login";
    }

    @RequestMapping("efetuaLogin")
    public String efetuaLogin(Usuario usuario, HttpSession session) {
        try(Connection con = new ConnectionFactory().getConnection()) {
            JdbcUsuarioDao dao = new JdbcUsuarioDao(con);
            if(dao.existeUsuario(usuario)) {
                session.setAttribute("usuarioLogado", usuario);
                return "menu";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:loginForm";
    }
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:loginForm";
    }
}
