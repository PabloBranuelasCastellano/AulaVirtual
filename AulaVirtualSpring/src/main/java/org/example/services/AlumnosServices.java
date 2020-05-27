package org.example.services;

import org.example.Entities.Alumnos;
import org.example.Entities.GruposAlumno;
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

    @Autowired
    DataSource dataSource=null;
    Connection connection=null;
    public  String PanelAlumnos(HttpServletRequest request, Model model)throws SQLException {
        alumnos=loginServices.getAlumnos();
        return MateriasAlumno(request, model);
    }

    public String MateriasAlumno(HttpServletRequest request,Model model)throws SQLException{
        connection=dataSource.getConnection();
        String Materiasporalumno="select m.Nombre, a.Usuario\n" +
                "from alumnos a " +
                "inner join gruposalumnos ga on a.AlumnoId = ga.AlumnoId\n" +
                "inner join grupos g on ga.GrupoId = g.GrupoId\n" +
                "inner join materias m on g.MateriaId = m.MateriaId\n" +
                "where a.AlumnoId = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(Materiasporalumno);
        preparedStatement.setInt(1,loginServices.getAlumnos().getIdAlumno());
        //System.out.println("Consulta preparada .El Id del alumno es "+loginServices.getAlumnos().getIdAlumno());
        ResultSet resultSet=preparedStatement.executeQuery();
        List<GruposAlumno> Materias_alumnos=new ArrayList<>();
        while(resultSet.next()){
            gruposAlumno=new GruposAlumno();
            //System.out.println(resultSet.getString(1));
            gruposAlumno.setMateriaNombre(resultSet.getString(1));
            Materias_alumnos.add(gruposAlumno);
        }
        model.addAttribute("Asignaturas_grupo",Materias_alumnos);
        //System.out.println("Ejecución completada correctamete");
        return "panelalumnos";
    }
}
