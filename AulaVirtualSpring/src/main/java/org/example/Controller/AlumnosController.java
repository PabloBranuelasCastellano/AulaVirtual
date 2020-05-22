package org.example.Controller;

import org.example.Entities.Alumnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AlumnosController {
    @Autowired
    LogginController logginController;
    Alumnos alumnos;
    @GetMapping("/homeAlumnos")
    public  String PanelAlumnos(HttpServletRequest request, Model model)throws SQLException{
        alumnos=logginController.alumnos;
        return "panelprofesores";
    }

}
