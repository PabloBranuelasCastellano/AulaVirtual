package org.example.Controller;

import org.example.Entities.Alumnos;
import org.example.Entities.Profesores;
import org.example.services.LoginServices;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class LogginController {

    @Autowired
    LoginServices loginServices;
    DataSource dataSource;
    Connection connection;
    Profesores profesores;
    Alumnos alumnos;
    String Email_Usuario;
    String Clave_Usuario;

    @GetMapping("/")
    public String InicioSesion(HttpServletRequest request, Model model) {

        return "Login";
    }

    @PostMapping("/Comprobar")
    public String ComprobarUsuario(HttpServletRequest request, Model model) throws SQLException {
        if (loginServices.ComprobarUsuario(request, model).equals("Profesor")) {
            return "redirect:/homeProfesores";
        }
        else if (loginServices.ComprobarUsuario(request, model).equals("Alumnos")) {

            return "redirect:/homeAlumnos";

        }
        else {
            return "Errores";
        }

    }











    @GetMapping("/logout")
    public String Cerrar_Sesion(HttpServletRequest request, HttpServletResponse response, Model model) {

        Cookie[] cookies = request.getCookies();
        HttpSession session=request.getSession();
        model.asMap().clear();
        session.removeAttribute("UsuarioConectado");
        session.invalidate();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");

            response.addCookie(cookie);

        }
        return "redirect:/";

    }
}