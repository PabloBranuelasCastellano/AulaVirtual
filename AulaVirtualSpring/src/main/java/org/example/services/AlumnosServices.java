package org.example.services;

import org.example.Entities.Alumnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class AlumnosServices {
    @Autowired
    LoginServices loginServices;

    Alumnos alumnos;


    @Autowired
    DataSource dataSource=null;
    Connection connection=null;
    public  String PanelAlumnos(HttpServletRequest request, Model model)throws SQLException {
        alumnos=loginServices.getAlumnos();
        return MateriasAlumno(request, model);
    }

    public String MateriasAlumno(HttpServletRequest request,Model model)throws SQLException{
        connection=dataSource.getConnection();
        String Materias_alumno="select distinct M.Nombre,m.MateriaId,m.Nombre ,n.Denominacion,n.NivelId  from temas t,niveles n,profesores p,materias m where (t.NivelId =n.NivelId and p.ProfesorId =t.ProfesorId and m.EsActiva =true and P.ProfesorId=?)";
        PreparedStatement preparedStatement=connection.prepareStatement(Materias_alumno);
        preparedStatement.setInt(1,loginServices.getAlumnos().getIdAlumno());
        //System.out.println("Consulta preparada .El Id del alumno es "+loginServices.getAlumnos().getIdAlumno());
        return "panelalumnos";
    }
}
