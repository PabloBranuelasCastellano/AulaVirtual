package org.example.Controller;

import org.example.Entities.Grupos;
import org.example.Entities.GruposAlumno;
import org.example.Entities.Profesores;
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
    LogginController logginController;
    Profesores profesores;
    GruposAlumno gruposAlumno=new GruposAlumno();
    Grupos grupos=new Grupos();

    @Autowired
    DataSource dataSource;
    Connection connection;

    @GetMapping("/homeProfesores")
    public String PanelProfesor(HttpServletRequest request, Model model)throws SQLException{

        profesores=logginController.profesores;
        /*System.out.println(profesores.getIdProfesor());
        System.out.println(profesores.getUsuarioProfesor());*/
        return MostrarGrupos(request, model);
    }

    public String MostrarGrupos(HttpServletRequest request,Model model)throws SQLException {
        connection=dataSource.getConnection();
        String VerGrupos="select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and P.ProfesorId=? )";
        PreparedStatement preparedStatement=connection.prepareStatement(VerGrupos);
        preparedStatement.setInt(1,logginController.profesores.getIdProfesor());
        //System.out.println(logginController.profesores.getIdProfesor());
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Grupos>gruposMateria = null;
        while(resultSet.next()){
            gruposMateria=new ArrayList<>();
            grupos.setIdGrupo(resultSet.getInt(1));
            grupos.setCursoAcademicoGrupo(resultSet.getString(2));
            grupos.setNombreGrupo(resultSet.getString(3));
            grupos.setNombreMateria(resultSet.getString(4));
            grupos.setNivelGrupo(resultSet.getString(5));
            grupos.setProfesorGrupo(resultSet.getString(6));
            gruposMateria.add(grupos);
        }
        model.addAttribute("Grupos_Materia",gruposMateria);

        return "panelprofesores";
    }

    @GetMapping("/VerAlumnos/{GrupoId}")
    public String AlumnosGrupos(HttpServletRequest request,Model model,@PathVariable int GrupoId)throws SQLException{
        //System.out.println("El id del grupo es "+GrupoId);
        connection=dataSource.getConnection();
        String Alumnos_Grupo="select a.AlumnoId ,a.Nombre,a.PrimerApellido ,a.SegundoApellido from alumnos a, gruposalumnos ga  ,grupos g  where (ga.GrupoId =g.GrupoId and ga.AlumnoId =a.AlumnoId and g.GrupoId=?) order by a.PrimerApellido asc";
        PreparedStatement preparedStatement=connection.prepareStatement(Alumnos_Grupo);
        preparedStatement.setInt(1,GrupoId);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<GruposAlumno>gruposAlumnoList=null;
        while(resultSet.next()){
            gruposAlumnoList=new ArrayList<>();
            gruposAlumno.setIdAlumno(resultSet.getInt(1));
            System.out.println("Id Alumno: "+resultSet.getInt(1));
            gruposAlumno.setNombreAlumno(resultSet.getString(2));
            System.out.println("Nombre Alumno: "+resultSet.getString(2));
            System.out.println("1º Apellido Alumno: "+resultSet.getString(3));
            gruposAlumno.setPrimerApellidoAlumno(resultSet.getString(3));
            System.out.println("2º Apellido Alumno: "+resultSet.getString(4));
            gruposAlumno.setSegundoApellidoAlumno(resultSet.getString(4));
            gruposAlumnoList.add(gruposAlumno);
        }
        model.addAttribute("Lista_Alumnos",gruposAlumnoList);
        return "Alumnos_Grupo";
    }





}
