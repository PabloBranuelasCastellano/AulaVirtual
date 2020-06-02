package org.example.Controller;

import org.example.Entities.Grupos;
import org.example.Entities.GruposAlumno;
import org.example.Entities.Profesores;
import org.example.services.LoginServices;
import org.example.services.ProfesoresServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfesorController {




    @Autowired
    LoginServices loginServices;
    @Autowired
    ProfesoresServices profesoresServices;

    @GetMapping("/homeProfesores")
    public String PanelProfesor(HttpServletRequest request, Model model)throws SQLException{
        return profesoresServices.PanelProfesor(request, model);
    }


    @GetMapping("/VerAlumnos/{GrupoId}")
    public String AlumnosGrupos(HttpServletRequest request,Model model,@PathVariable int GrupoId)throws SQLException{
        return profesoresServices.AlumnosGrupos(request, model, GrupoId);
    }

    @GetMapping("/Gruposnoactivos")
    public String VerGruposDesactivados(HttpServletRequest request,Model model)throws SQLException{
        return profesoresServices.VerGruposDesactivados(request, model);
    }

    @GetMapping("/NuevoTema/{MateriaId}/{NivelId}/{ProfesorId}")
    public String CrearTema(HttpServletRequest request,Model model,@PathVariable int MateriaId,@PathVariable int NivelId,@PathVariable int ProfesorId){
        System.out.println("El id de la Asignatura es "+MateriaId);
        System.out.println("El id del nivel es  "+NivelId);
        System.out.println("El id del Profesor es  "+ProfesorId);
        return profesoresServices.CrearTema(request, model, MateriaId, NivelId, ProfesorId);
    }
    @PostMapping("/NuevoTema")
    public String RegistrarTema(HttpServletRequest request,Model model) throws SQLException {

        return profesoresServices.RegistrarTema(request, model);
    }

    @GetMapping("/VerTemas/{MateriaId}")
    public  String TemasProfesor(HttpServletRequest request,Model model,@PathVariable int MateriaId)throws SQLException{
        return profesoresServices.TemasProfesor(request, model,MateriaId);
    }








}
