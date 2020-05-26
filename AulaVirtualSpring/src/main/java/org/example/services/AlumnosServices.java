package org.example.services;

import org.example.Entities.Alumnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class AlumnosServices {
    @Autowired
    LoginServices loginServices;
    DataSource dataSource=null;
    Connection connection=null;
    Alumnos alumnos;

    public  String PanelAlumnos(HttpServletRequest request, Model model)throws SQLException {
        alumnos=loginServices.getAlumnos();
        return MateriasAlumno(request, model);
    }

    public String MateriasAlumno(HttpServletRequest request,Model model)throws SQLException{
        connection=dataSource.getConnection();
        return "panelalumnos";
    }
}
