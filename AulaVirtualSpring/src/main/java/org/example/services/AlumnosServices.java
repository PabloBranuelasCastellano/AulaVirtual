package org.example.services;

import org.example.Entities.Alumnos;
import org.example.Entities.GruposAlumno;
import org.example.Entities.Materias;
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
        }
        model.addAttribute("MateriaAlumno",materiasList);
        return "panelalumnos";
    }
}
