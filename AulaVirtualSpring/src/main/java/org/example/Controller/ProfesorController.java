package org.example.Controller;

import org.example.Entities.Profesores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfesorController {

    @Autowired
    LogginController logginController;
    Profesores profesores;

    @GetMapping("/homeProfesores")
    public String PanelProfesor(HttpServletRequest request, Model model){
        profesores=logginController.profesores;
        System.out.println(profesores.getIdProfesor());
        System.out.println(profesores.getUsuarioProfesor());
        return "panelprofesores";
    }


}
