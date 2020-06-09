package org.example.Controller;

import org.example.Entities.Alumnos;
import org.example.services.AlumnosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class AlumnosController {
    @Autowired
    LogginController logginController;
    Alumnos alumnos;
    @Autowired
    AlumnosServices alumnosServices;

    @GetMapping("/homeAlumnos")
    public String PanelAlumnos(HttpServletRequest request, Model model) throws SQLException {
        return alumnosServices.PanelAlumnos(request, model);
    }

    public String MateriasAlumnos(HttpServletRequest request, Model model) throws SQLException {
        return alumnosServices.MateriasAlumnos(request, model);
    }

    @GetMapping("VerTemasAlumnos/{ProfesorId}/{MateriaId}/{NivelId}")
    public String TemasAlumnos(HttpServletRequest request, Model model, @PathVariable int ProfesorId,@PathVariable int MateriaId,@PathVariable int NivelId) throws SQLException {
        return alumnosServices.TemasAlumnos(request, model, ProfesorId,MateriaId,NivelId);
    }
    @GetMapping("/Puntos_TemaAlumnos/{IdTema}")
    public String Puntos_Tema(HttpServletRequest request, Model model, @PathVariable int IdTema) throws SQLException {
        return alumnosServices.Puntos_Tema(request, model, IdTema);
    }
}
