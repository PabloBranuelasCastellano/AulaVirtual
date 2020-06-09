package org.example.Controller;

import org.example.Entities.Alumnos;
import org.example.Entities.Profesores;
import org.example.services.LoginServices;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
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
    @ResponseBody
    public String InicioSesion(HttpServletRequest request, Model model) {

        return "Login";
    }

    @PostMapping("/Comprobar")
    public String ComprobarUsuario(HttpServletRequest request, Model model) throws SQLException {
            loginServices.ComprobarUsuario(request,model);
            if(loginServices.getRol()==null) {
                return "Errores";

            }



        else if(loginServices.getRol().equals("Alumnos")) {
                return "redirect:/homeAlumnos";
        }

        else{
            return "redirect:/homeProfesores";
        }

    }

    @GetMapping("/logout")
    public String Cerrar_Sesion(HttpServletRequest request, HttpServletResponse response, Model model) {
        loginServices.Cerrar_Sesion(request, response, model);

        if(loginServices.Cerrar_Sesion(request, response, model).equals("Sesion Cerrada")) {

            return "redirect:/";
        }
        return "Fallo al cerrar la sesion";
    }
}