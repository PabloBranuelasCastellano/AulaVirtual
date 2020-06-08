package org.example.services;

import org.example.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnosServices {
    @Autowired
    LoginServices loginServices;

    Alumnos alumnos;
    GruposAlumno gruposAlumno;
    Materias materias;
    Temas temas;
    PuntosTema puntosTema;
    @Autowired
    DataSource dataSource=null;
    Connection connection=null;
    public  String PanelAlumnos(HttpServletRequest request, Model model)throws SQLException {
        alumnos=loginServices.getAlumnos();
        return GruposAlumno(request, model);
    }

    public String GruposAlumno(HttpServletRequest request,Model model)throws SQLException{
        connection=dataSource.getConnection();
        String Materiasporalumno="select m.Nombre, a.Usuario,g.profesorId,g.NivelId,g.materiaId\n" +
        "from alumnos a " +
                "inner join gruposalumnos ga on a.AlumnoId = ga.AlumnoId\n" +
                "inner join grupos g on ga.GrupoId = g.GrupoId\n" +
                "inner join materias m on g.MateriaId = m.MateriaId\n" +
                "where (a.AlumnoId = ? and m.EsActiva=true)";
        PreparedStatement preparedStatement=connection.prepareStatement(Materiasporalumno);
        preparedStatement.setInt(1,loginServices.getAlumnos().getIdAlumno());
        //System.out.println("Consulta preparada .El Id del alumno es "+loginServices.getAlumnos().getIdAlumno());
        ResultSet resultSet=preparedStatement.executeQuery();
        List<GruposAlumno> gruposAlumnoList=new ArrayList<>();
        while(resultSet.next()){
            gruposAlumno=new GruposAlumno();
            //System.out.println(resultSet.getString(1));
            gruposAlumno.setMateriaNombre(resultSet.getString(1));
            gruposAlumnoList.add(gruposAlumno);
        }
        model.addAttribute("Asignaturas_grupo",gruposAlumnoList);
        //System.out.println("Ejecución completada correctamete");
        return  MateriasAlumnos(request, model);
    }

    public String MateriasAlumnos(HttpServletRequest request, Model model) throws SQLException{
        connection=dataSource.getConnection();
        String Materias_Alumnos="select m.Nombre, a.Usuario,g.profesorId,g.NivelId,g.materiaId\n" +
                "from alumnos a " +
                "inner join gruposalumnos ga on a.AlumnoId = ga.AlumnoId\n" +
                "inner join grupos g on ga.GrupoId = g.GrupoId\n" +
                "inner join materias m on g.MateriaId = m.MateriaId\n" +
                "where (a.AlumnoId = ? and m.EsActiva=true)";
        PreparedStatement preparedStatement=connection.prepareStatement(Materias_Alumnos);
        preparedStatement.setInt(1,loginServices.getAlumnos().getIdAlumno());
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Materias>materiasList=new ArrayList<>();
        while (resultSet.next()){
            materias=new Materias();
            materias.setMateriaId(resultSet.getInt("MateriaId"));
            materias.setNombreMateria(resultSet.getString("Nombre"));
            materias.setNivelId(resultSet.getInt("NivelId"));
            materias.setProfesorId(resultSet.getInt("ProfesorId"));
            //System.out.println("El id del nivel educativo es "+resultSet.getInt("NivelId"));
            materiasList.add(materias);
        }
        model.addAttribute("MateriaAlumno",materiasList);
        return "panelalumnos";
    }
    public String TemasAlumnos(HttpServletRequest request, Model model, int ProfesorId,int MateriaId,int NivelId) throws SQLException {
        connection = dataSource.getConnection();
        String Ver_Temas = "select distinct t.MateriaId,t.profesorId,t.titulo,m.Nombre,t.TemaId,t.NivelId ,t.EsActivo from temas t, profesores p ,niveles n,materias m where(t.materiaId=m.MateriaId and n.nivelId=t.nivelId and t.profesorId=? and t.materiaId=? and t.EsActivo=true)";
        PreparedStatement preparedStatement = connection.prepareStatement(Ver_Temas);
        preparedStatement.setInt(1,materias.getProfesorId());
        preparedStatement.setInt(2, MateriaId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Temas> temasList = new ArrayList<>();
        while (resultSet.next()) {
            temas = new Temas();
            temas.setTemaId(resultSet.getInt("TemaId"));
            //System.out.println("Id del Tema "+resultSet.getInt("TemaId"));
            temas.setTituloTema(resultSet.getString("Titulo"));
            //System.out.println("Titulo del Tema "+resultSet.getString("Titulo"));
            temas.setProfesorId(resultSet.getInt("ProfesorId"));
            //System.out.println("Id del Profesor "+resultSet.getInt("ProfesorId"));
            temas.setNivelId(resultSet.getInt("NivelId"));
            //System.out.println("El id del Nivel es "+resultSet.getInt("NivelId"));
            temas.setMateriaId(resultSet.getInt("MateriaId"));
            //System.out.println("El id de la Materia es "+resultSet.getInt("MateriaId"));
            temas.setTemaActivo(resultSet.getBoolean("EsActivo"));
            //System.out.println("Esta el tema activado? "+resultSet.getBoolean("EsActivo"));
            temasList.add(temas);
            model.addAttribute("ListaTemas", temasList);


        }
        return "Desplegar_TemasAlumnos";
    }

    public String Puntos_Tema(HttpServletRequest request, Model model, int TemaId) throws SQLException {
        connection = dataSource.getConnection();
        String Ver_Puntos = "select pnt.TemaId,pnt.PuntoId,pnt.Titulo,pnt.resumen,pnt.texto,pnt.EsActivo from puntos pnt,temas t,profesores p where(pnt.temaId=t.temaId and t.profesorId=p.profesorId and t.materiaId=? and t.profesorId=? and pnt.TemaId=? and pnt.EsActivo=true)order by pnt.orden asc";
        PreparedStatement preparedStatement = connection.prepareStatement(Ver_Puntos);
        preparedStatement.setInt(1, temas.getMateriaId());
        preparedStatement.setInt(2, temas.getProfesorId());
        preparedStatement.setInt(3, TemaId);
        //System.out.println("EL id de la Materia es " + temas.getMateriaId() + " el Id del Profesor es " + temas.getProfesorId() + " y el Id del tema Es " + temas.getTemaId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<PuntosTema> puntosTemaList = new ArrayList<>();
        while (resultSet.next()) {
            puntosTema = new PuntosTema();

            //System.out.println("El id del tema es " + temas.getTemaId());
            puntosTema.setIdPunto(resultSet.getInt("PuntoId"));
            puntosTema.setTemaId(resultSet.getInt("TemaId"));
            //System.out.println(resultSet.getString("Titulo"));
            puntosTema.setTituloPunto(resultSet.getString("Titulo"));
            //System.out.println(resultSet.getString("Resumen"));
            puntosTema.setResumenPunto(resultSet.getString("Resumen"));
            //System.out.println(resultSet.getString("Texto"));
            puntosTema.setTextoPunto(resultSet.getString("Texto"));
            puntosTema.setPuntoActivo(resultSet.getBoolean("EsActivo"));
            puntosTemaList.add(puntosTema);
            //System.out.println("Guardamos los datos cogidos y los enviamos al model");
        }
        model.addAttribute("PuntosTema", puntosTemaList);

        return "Contenido_TemasAlumnos";
    }

}
