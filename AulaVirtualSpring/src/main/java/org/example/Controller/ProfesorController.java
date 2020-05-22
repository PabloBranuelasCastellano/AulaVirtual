package org.example.Controller;

import org.example.Entities.Grupos;
import org.example.Entities.Profesores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    Grupos grupos;

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
        List<Grupos>gruposMateria;
        while(resultSet.next()){
            gruposMateria=new ArrayList<>();

            grupos.setCursoAcademicoGrupo(resultSet.getString(2));
            grupos.setNombreGrupo(resultSet.getString(3));
            grupos.setNombreMateria(resultSet.getString(4));
            grupos.setNivelGrpo(resultSet.getString(6));
            grupos.setProfesorGrupo(resultSet.getString(6));
            gruposMateria.add(grupos);
        }

        return "panelprofesores";
    }






}
