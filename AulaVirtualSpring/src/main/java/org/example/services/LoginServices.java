package org.example.services;

import org.example.Entities.Alumnos;
import org.example.Entities.Profesores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Service
public class LoginServices {

    @Autowired
    DataSource dataSource;
    Connection connection;
    Profesores profesores;
    Alumnos alumnos;
    String rol;

    public Alumnos getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Alumnos alumnos) {
        this.alumnos = alumnos;
    }

    public Profesores getProfesores() {
        return profesores;
    }

    public void setProfesores(Profesores profesores) {
        this.profesores = profesores;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String ComprobarUsuario(HttpServletRequest request, Model model) throws SQLException {

        LocalDate localDate = LocalDate.now();
        String Email_Usuario = request.getParameter("Email_Acceso");
        String Clave_Usuario = request.getParameter("Clave_Acceso");
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
            rol = "Profesores";
            setRol(rol);
            System.out.println("Fecha Actual "+localDate);
            String Actualizar_Curso="update cursosacademicos set EsActivo=false where FechaFin <(select current_Date())";
            preparedStatement=connection.prepareStatement(Actualizar_Curso);
            preparedStatement.executeUpdate();
            System.out.println("Curso Actualizado");
            return rol;
        } else {
            Email_Usuario = request.getParameter("Email_Acceso");
            Clave_Usuario = request.getParameter("Clave_Acceso");
            connection = dataSource.getConnection();
            String Alumno = "Select * from Alumnos where Email=? and Password=md5(?)";
            preparedStatement = connection.prepareStatement(Alumno);
            preparedStatement.setString(1, Email_Usuario);
            preparedStatement.setString(2, Clave_Usuario);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                alumnos = new Alumnos();
                alumnos.setUsuarioAlumno(resultSet.getString("Usuario"));
                alumnos.setIdAlumno(resultSet.getInt("AlumnoId"));
                HttpSession session = request.getSession();
                session.setAttribute("UsuarioConectado", alumnos);
                rol = "Alumnos";
                setRol(rol);
                return rol;

            }

        }

        rol = null;
        setRol(rol);
        return rol;
    }

    public String Cerrar_Sesion(HttpServletRequest request, HttpServletResponse response, Model model) {


        HttpSession session =request.getSession();
        model.asMap().clear();
        session.removeAttribute("UsuarioConectado");

        session.invalidate();

        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();

        if( cookies != null ) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                System.out.print("Deleted cookie: " +
                        cookie.getName( ) + "<br/>");

            }
        }
        if(rol.equals("Profesores")) {
            profesores.setUsuarioProfesor(null);
            profesores.setIdProfesor(0);
        }
        else {
            alumnos.setUsuarioAlumno(null);
            alumnos.setIdAlumno(0);
        }
        return "Sesion Cerrada";

    }
}
