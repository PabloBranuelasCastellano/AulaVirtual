package org.example.services;

import org.example.Entities.Grupos;
import org.example.Entities.GruposAlumno;
import org.example.Entities.Materias;
import org.example.Entities.Profesores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesoresServices {
    @Autowired
    LoginServices loginServices;
    Profesores profesores;
    Grupos grupos;
    GruposAlumno gruposAlumno;
    Materias materias;
    @Autowired
    DataSource dataSource=null;
    Connection connection=null;

    public String PanelProfesor(HttpServletRequest request, Model model)throws SQLException {

        profesores=loginServices.getProfesores();
        /*System.out.println(profesores.getIdProfesor());
        System.out.println(profesores.getUsuarioProfesor());*/
        return MostrarGrupos(request, model);
    }
    public String MostrarGrupos(HttpServletRequest request,Model model)throws SQLException {
        connection=dataSource.getConnection();

        String VerGrupos="select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and P.ProfesorId=?)";

        PreparedStatement preparedStatement=connection.prepareStatement(VerGrupos);
        preparedStatement.setInt(1,profesores.getIdProfesor());
        //System.out.println("Consulta preparada");

        //System.out.println(logginController.profesores.getIdProfesor());
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Grupos> gruposMateria=new ArrayList<>();
        //System.out.println("Lanzamos la consulta");

        while(resultSet.next()){
            grupos=new Grupos();
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
        //System.out.println("Recorremos el bucle .guardamos los valores");


        model.addAttribute("Grupos_Materia",gruposMateria);

        //System.out.println("LLegamos al final y funciona");
        return MateriasProfesor(request, model);
    }

    public String MateriasProfesor(HttpServletRequest request,Model model)throws SQLException{
        connection=dataSource.getConnection();
        String VerMaterias="select M.Nombre as Asignatura,M.MateriaId ,N.Denominacion as NivelEducativo ,n.NivelId,p.ProfesorId from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and P.ProfesorId=?)";
        PreparedStatement preparedStatement=connection.prepareStatement(VerMaterias);
        preparedStatement.setInt(1,profesores.getIdProfesor());
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Materias>Materias_Profesor=new ArrayList<>();
        while(resultSet.next()){
            materias=new Materias();
            materias.setNombreMateria(resultSet.getString(1));
            materias.setMateriaId(resultSet.getInt(2));
            materias.setNivelId(resultSet.getInt(4));
            materias.setProfesorId(resultSet.getInt(5));
            //System.out.println("Nombre de la Asignatura "+resultSet.getString(1));
            //System.out.println("Materia Id "+resultSet.getInt(2));
            //System.out.println("Nivel Educativo "+resultSet.getString(3));
            //System.out.println("Id del Nivel "+resultSet.getInt(4));
            //System.out.println("Id del Profesor "+resultSet.getInt(5));
            Materias_Profesor.add(materias);

        }
        model.addAttribute("materiasprofesor",Materias_Profesor);
        System.out.println("Materias agreadas a la lista y enviadas al modelo");
        return "panelprofesores";
    }


    public String VerGruposDesactivados(HttpServletRequest request,Model model)throws SQLException{
        connection=dataSource.getConnection();
        String GruposNoActivos="select  ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =false and P.ProfesorId=? )";
        PreparedStatement preparedStatement=connection.prepareStatement(GruposNoActivos);
        preparedStatement.setInt(1,profesores.getIdProfesor());
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

    public String AlumnosGrupos(HttpServletRequest request,Model model,int GrupoId)throws SQLException{
        //System.out.println("El id del grupo es "+GrupoId);
        connection=dataSource.getConnection();
        String Alumnos_Grupo="select a.AlumnoId ,a.Nombre,a.PrimerApellido ,a.SegundoApellido from alumnos a, gruposalumnos ga  ,grupos g  where (ga.GrupoId =g.GrupoId and ga.AlumnoId =a.AlumnoId and g.GrupoId=?) order by a.PrimerApellido asc";
        PreparedStatement preparedStatement=connection.prepareStatement(Alumnos_Grupo);
        preparedStatement.setInt(1,GrupoId);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<GruposAlumno>gruposAlumnoList=null;
        gruposAlumnoList=new ArrayList<>();
        while(resultSet.next()){
            gruposAlumno=new GruposAlumno();
            gruposAlumno.setIdAlumno(resultSet.getInt(1));
            gruposAlumno.setNombreAlumno(resultSet.getString(2));
            gruposAlumno.setPrimerApellidoAlumno(resultSet.getString(3));
            gruposAlumno.setSegundoApellidoAlumno(resultSet.getString(4));
            gruposAlumnoList.add(gruposAlumno);
        }
        model.addAttribute("Lista_Alumnos",gruposAlumnoList);
        return "Alumnos_Grupo";
    }
    public String CrearTema(HttpServletRequest request,Model model,int MateriaId,int NivelId,int ProfesorId){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        materias.setMateriaId(MateriaId);
        materias.setNivelId(NivelId);
        materias.setProfesorId(ProfesorId);
        return "NuevoTema";
    }
    public String RegistrarTema(HttpServletRequest request,Model model){
        System.out.println("El Id de la Materia es "+materias.getMateriaId()+"el del Profesor es "+profesores.getIdProfesor()+" y el del nivel es "+materias.getNivelId());
        return "NuevoTema";
    }
}
