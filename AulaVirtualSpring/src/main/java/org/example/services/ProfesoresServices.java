package org.example.services;

import org.example.Entities.Grupos;
import org.example.Entities.GruposAlumno;
import org.example.Entities.Profesores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class ProfesoresServices {
    @Autowired
    LoginServices loginServices;
    Profesores profesores;
    Grupos grupos;
    GruposAlumno gruposAlumno;
    DataSource dataSource;
    Connection connection;

    public String PanelProfesor(HttpServletRequest request, Model model)throws SQLException {

        profesores=loginServices.getProfesores();
        /*System.out.println(profesores.getIdProfesor());
        System.out.println(profesores.getUsuarioProfesor());*/
        return "panelprofesores";
    }
}
