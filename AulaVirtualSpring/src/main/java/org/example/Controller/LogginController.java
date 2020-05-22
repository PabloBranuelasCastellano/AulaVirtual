package org.example.Controller;

import org.example.Entities.Alumnos;
import org.example.Entities.Profesores;
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


        Email_Usuario = request.getParameter("Email_Acceso");
        Clave_Usuario = request.getParameter("Clave_Acceso");
        connection = dataSource.getConnection();
        String Profesor = "Select * from profesores where Email=? and Password=md5(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(Profesor);
        preparedStatement.setString(1, Email_Usuario);
        preparedStatement.setString(2, Clave_Usuario);

        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            profesores = new Profesores();
            profesores.setUsuarioProfesor(resultSet.getString("Usuario"));
            profesores.setIdProfesor(resultSet.getInt("ProfesorId"));
            HttpSession session = request.getSession();
            session.setAttribute("UsuarioConectado", profesores);

            return "redirect:/homeProfesores";
        }
        else{
            Email_Usuario = request.getParameter("Email_Acceso");
            Clave_Usuario = request.getParameter("Clave_Acceso");
            connection = dataSource.getConnection();
            String Alumno = "Select * from Alumnos where Email=? and Password=md5(?)";
            preparedStatement = connection.prepareStatement(Alumno);
            preparedStatement.setString(1, Email_Usuario);
            preparedStatement.setString(2, Clave_Usuario);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                alumnos=new Alumnos();
                alumnos.setUsuarioAlumno(resultSet.getString("Usuario"));
            }

        }

    return "Login";
        }









    @GetMapping("/logout/{idProfesor}")
    public String Cerrar_Sesion(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable int idProfesor ) {
        System.out.println("El Id es "+idProfesor);
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
        return "Login";

    }
}