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
    ArrayList<Grupos>gruposMateria=new ArrayList<Grupos>();
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
        String VerGrupos="select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and P.ProfesorId=?)";
        PreparedStatement preparedStatement=connection.prepareStatement(VerGrupos);
        preparedStatement.setInt(1,logginController.profesores.getIdProfesor());
        //System.out.println(logginController.profesores.getIdProfesor());
        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){

            System.out.print(resultSet.getInt(1)+" ");
            grupos.setIdGrupo(resultSet.getInt(1));
            System.out.print(resultSet.getString(2)+" ");
            grupos.setCursoAcademicoGrupo(resultSet.getString(2));
            System.out.print(resultSet.getString(3)+" ");
            grupos.setNombreGrupo(resultSet.getString(3));
            grupos.setNombreMateria(resultSet.getString(4));
            System.out.print(resultSet.getString(4)+" ");
            System.out.print(resultSet.getString(5)+" ");
            grupos.setNivelGrupo(resultSet.getString(5));
            System.out.print(resultSet.getString(6)+" ");
            grupos.setProfesorGrupo(resultSet.getString(6));
            System.out.println();
            gruposMateria.add(grupos);
        }
        for (Grupos cancion : gruposMateria) {
            System.out.println(cancion);
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
            gruposAlumno.setNombreAlumno(resultSet.getString(2));
            gruposAlumno.setPrimerApellidoAlumno(resultSet.getString(3));
            gruposAlumno.setSegundoApellidoAlumno(resultSet.getString(4));
            gruposAlumnoList.add(gruposAlumno);
        }
        model.addAttribute("Lista_Alumnos",gruposAlumnoList);
        return "Alumnos_Grupo";
    }

    @GetMapping("/Gruposnoactivos")
    public String VerGruposDesactivados(HttpServletRequest request,Model model)throws SQLException{
        connection=dataSource.getConnection();
        String GruposNoActivos="select  ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =false and P.ProfesorId=? )";
        PreparedStatement preparedStatement=connection.prepareStatement(GruposNoActivos);
        preparedStatement.setInt(1,logginController.profesores.getIdProfesor());
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Grupos>GruposDesactivados=null;
        while(resultSet.next()){
            GruposDesactivados=new ArrayList<>();
            grupos.setCursoAcademicoGrupo(resultSet.getString(1));
            grupos.setNombreGrupo(resultSet.getString(2));
            grupos.setNombreMateria(resultSet.getString(3));
            grupos.setNivelGrupo(resultSet.getString(4));
            grupos.setProfesorGrupo(resultSet.getString(5));
            GruposDesactivados.add(grupos);

        }
        model.addAttribute("GruposDesactivados",GruposDesactivados);
        return "Grupos_no_activos";
    }





}
