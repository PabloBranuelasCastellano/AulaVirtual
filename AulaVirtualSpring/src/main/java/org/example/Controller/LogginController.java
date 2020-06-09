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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    public ModelAndView InicioSesion(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Login");
        return modelAndView;

    }

    @PostMapping("/Comprobar")
    public ModelAndView ComprobarUsuario(HttpServletRequest request, Model model) throws SQLException {
            loginServices.ComprobarUsuario(request,model);
            ModelAndView modelAndView = new ModelAndView();

            if(loginServices.getRol()==null) {
                modelAndView.setViewName("Errores");
                return modelAndView;


            }



        else if(loginServices.getRol().equals("Alumnos")) {
                modelAndView.setViewName("redirect:/homeAlumnos");
                return modelAndView;

        }

        else{
            modelAndView.setViewName("redirect:/homeProfesores");
            return modelAndView;

        }

    }

    @GetMapping("/logout")
    public ModelAndView Cerrar_Sesion(HttpServletRequest request, HttpServletResponse response, Model model) {
        loginServices.Cerrar_Sesion(request, response, model);
        ModelAndView modelAndView = new ModelAndView();

        if(loginServices.Cerrar_Sesion(request, response, model).equals("Sesion Cerrada")) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;

        }
        return null;
    }
}