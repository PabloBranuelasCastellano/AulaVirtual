package org.example.services;

import org.example.Entities.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
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
    Temas temas;
    PuntosTema puntosTema;
    @Autowired
    DataSource dataSource = null;
    Connection connection = null;
    private boolean Activado;

    public boolean isActivado() {
        return Activado;
    }

    public void setActivado(boolean activado) {
        Activado = activado;
    }

    public String PanelProfesor(HttpServletRequest request, Model model) throws SQLException {

        profesores = loginServices.getProfesores();
        /*System.out.println(profesores.getIdProfesor());
        System.out.println(profesores.getUsuarioProfesor());*/
        return MostrarGrupos(request, model);
    }

    public String MostrarGrupos(HttpServletRequest request, Model model) throws SQLException {
        connection = dataSource.getConnection();

        String VerGrupos = "select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and P.ProfesorId=?)";

        PreparedStatement preparedStatement = connection.prepareStatement(VerGrupos);
        preparedStatement.setInt(1, profesores.getIdProfesor());
        //System.out.println("Consulta preparada");

        //System.out.println(logginController.profesores.getIdProfesor());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Grupos> gruposMateria = new ArrayList<>();
        //System.out.println("Lanzamos la consulta");

        while (resultSet.next()) {
            grupos = new Grupos();
            System.out.print(resultSet.getInt(1) + " ");
            grupos.setIdGrupo(resultSet.getInt(1));
            System.out.print(resultSet.getString(2) + " ");
            grupos.setCursoAcademicoGrupo(resultSet.getString(2));
            System.out.print(resultSet.getString(3) + " ");
            grupos.setNombreGrupo(resultSet.getString(3));
            grupos.setNombreMateria(resultSet.getString(4));
            System.out.print(resultSet.getString(4) + " ");
            System.out.print(resultSet.getString(5) + " ");
            grupos.setNivelGrupo(resultSet.getString(5));
            System.out.print(resultSet.getString(6) + " ");
            grupos.setProfesorGrupo(resultSet.getString(6));
            System.out.println();
            gruposMateria.add(grupos);

        }
        //System.out.println("Recorremos el bucle .guardamos los valores");


        model.addAttribute("Grupos_Materia", gruposMateria);

        //System.out.println("LLegamos al final y funciona");
        return MateriasProfesor(request, model);
    }

    public String MateriasProfesor(HttpServletRequest request, Model model) throws SQLException {
        connection = dataSource.getConnection();
        String VerMaterias = "select distinct M.MateriaId,M.Nombre as Asignatura,N.Denominacion as NivelEducativo ,n.NivelId,p.ProfesorId from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true  and P.ProfesorId=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(VerMaterias);
        preparedStatement.setInt(1, profesores.getIdProfesor());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Materias> Materias_Profesor = new ArrayList<>();
        while (resultSet.next()) {
            materias = new Materias();
            materias.setMateriaId(resultSet.getInt("MateriaId"));
            materias.setNombreMateria(resultSet.getString("Asignatura"));
            materias.setNivelId(resultSet.getInt("NivelId"));
            materias.setProfesorId(resultSet.getInt("ProfesorId"));
            //System.out.println("Nombre de la Asignatura "+resultSet.getString(1));
            //System.out.println("Materia Id "+resultSet.getInt(2));
            //System.out.println("Nivel Educativo "+resultSet.getString(3));
            //System.out.println("Id del Nivel "+resultSet.getInt(4));
            //System.out.println("Id del Profesor "+resultSet.getInt(5));
            Materias_Profesor.add(materias);

        }
        model.addAttribute("materiasprofesor", Materias_Profesor);
        //System.out.println("Materias agreadas a la lista y enviadas al modelo");
        return "panelprofesores";
    }


    public String VerGruposDesactivados(HttpServletRequest request, Model model) throws SQLException {
        connection = dataSource.getConnection();
        String GruposNoActivos = "select  ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =false and P.ProfesorId=? )";
        PreparedStatement preparedStatement = connection.prepareStatement(GruposNoActivos);
        preparedStatement.setInt(1, profesores.getIdProfesor());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Grupos> GruposDesactivados = null;
        while (resultSet.next()) {
            GruposDesactivados = new ArrayList<>();
            grupos.setCursoAcademicoGrupo(resultSet.getString(1));
            grupos.setNombreGrupo(resultSet.getString(2));
            grupos.setNombreMateria(resultSet.getString(3));
            grupos.setNivelGrupo(resultSet.getString(4));
            grupos.setProfesorGrupo(resultSet.getString(5));
            GruposDesactivados.add(grupos);

        }
        model.addAttribute("GruposDesactivados", GruposDesactivados);
        return "Grupos_no_activos";
    }

    public String AlumnosGrupos(HttpServletRequest request, Model model, int GrupoId) throws SQLException {
        //System.out.println("El id del grupo es "+GrupoId);
        connection = dataSource.getConnection();
        String Alumnos_Grupo = "select a.AlumnoId ,a.Nombre,a.PrimerApellido ,a.SegundoApellido from alumnos a, gruposalumnos ga  ,grupos g  where (ga.GrupoId =g.GrupoId and ga.AlumnoId =a.AlumnoId and g.GrupoId=?) order by a.PrimerApellido asc";
        PreparedStatement preparedStatement = connection.prepareStatement(Alumnos_Grupo);
        preparedStatement.setInt(1, GrupoId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<GruposAlumno> gruposAlumnoList = null;
        gruposAlumnoList = new ArrayList<>();
        while (resultSet.next()) {
            gruposAlumno = new GruposAlumno();
            gruposAlumno.setIdAlumno(resultSet.getInt(1));
            gruposAlumno.setNombreAlumno(resultSet.getString(2));
            gruposAlumno.setPrimerApellidoAlumno(resultSet.getString(3));
            gruposAlumno.setSegundoApellidoAlumno(resultSet.getString(4));
            gruposAlumnoList.add(gruposAlumno);
        }
        model.addAttribute("Lista_Alumnos", gruposAlumnoList);
        return "Alumnos_Grupo";
    }

    public String CrearTema(HttpServletRequest request, Model model, int MateriaId, int NivelId, int ProfesorId) {


        materias.setMateriaId(MateriaId);
        materias.setNivelId(NivelId);
        materias.setProfesorId(ProfesorId);
        return "NuevoTema";
    }

    public String RegistrarTema(HttpServletRequest request, Model model) throws SQLException {
        LocalDate localDate = LocalDate.now();
        connection = dataSource.getConnection();
        String Agregar_Tema = "insert into Temas values(null,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(Agregar_Tema);
        preparedStatement.setString(1, request.getParameter("tema_Name"));
        preparedStatement.setString(2, request.getParameter("tema_Resumen"));
        preparedStatement.setInt(3, materias.getProfesorId());
        preparedStatement.setInt(4, materias.getMateriaId());
        preparedStatement.setInt(5, materias.getNivelId());
        preparedStatement.setString(6, localDate.toString());
        preparedStatement.setBoolean(7, Boolean.parseBoolean(request.getParameter("activar_Tema")));
        preparedStatement.setInt(8, Integer.parseInt(request.getParameter("tema_Number")));
        preparedStatement.setInt(9, Integer.parseInt(request.getParameter("tema_order")));
        //System.out.println("Cargamos los datos del formulario");

        preparedStatement.execute();

        return "NuevoTema";
    }

    public String TemasProfesor(HttpServletRequest request, Model model, int ProfesorId,int MateriaId,int NivelId) throws SQLException {
        connection = dataSource.getConnection();
        String Ver_Temas = "select distinct t.MateriaId,t.profesorId,t.titulo,m.Nombre,t.TemaId,t.NivelId ,t.EsActivo from temas t, profesores p ,niveles n,materias m where(t.materiaId=m.MateriaId and n.nivelId=t.nivelId and t.profesorId=? and t.materiaId=? and t.NivelId=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(Ver_Temas);
        preparedStatement.setInt(1, profesores.getIdProfesor());
        preparedStatement.setInt(2, MateriaId);
        preparedStatement.setInt(3,NivelId);

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
            temasList.add(temas);
            }


            model.addAttribute("ListaTemas", temasList);
            return "Desplegar_Temas";
        }
        public String Puntos_Tema(HttpServletRequest request, Model model, int TemaId) throws SQLException {
               connection = dataSource.getConnection();
               String Ver_Puntos = "select pnt.TemaId,pnt.PuntoId,pnt.Titulo,pnt.resumen,pnt.texto,pnt.EsActivo from puntos pnt,temas t,profesores p where(pnt.temaId=t.temaId and t.profesorId=p.profesorId and t.materiaId=? and t.profesorId=? and pnt.TemaId=?)order by pnt.orden asc";
               PreparedStatement preparedStatement = connection.prepareStatement(Ver_Puntos);
               preparedStatement.setInt(1, temas.getMateriaId());
               preparedStatement.setInt(2, temas.getProfesorId());
               preparedStatement.setInt(3, TemaId);
               ResultSet resultSet = preparedStatement.executeQuery();
               List<PuntosTema> puntosTemaList = new ArrayList<>();
               while (resultSet.next()) {
                   puntosTema = new PuntosTema();
                   puntosTema.setIdPunto(resultSet.getInt("PuntoId"));
                   puntosTema.setTemaId(resultSet.getInt("TemaId"));
                   puntosTema.setTituloPunto(resultSet.getString("Titulo"));
                   puntosTema.setResumenPunto(resultSet.getString("Resumen"));
                    puntosTema.setTextoPunto(resultSet.getString("Texto"));
                    puntosTema.setPuntoActivo(resultSet.getBoolean("EsActivo"));
                    puntosTemaList.add(puntosTema);
               }
               model.addAttribute("PuntosTema", puntosTemaList);
               return "Contenido_Temas";
        }

    public String ActivarTema(HttpServletRequest request, Model model, int MateriaId, int TemaId, int ProfesorId, int NivelId) throws SQLException {
        connection=dataSource.getConnection();
        String Cambiar_Estado="update temas t set EsActivo=true where (TemaId=? and profesorId=? and NivelId=? and MateriaId=?)";
        PreparedStatement preparedStatement=connection.prepareStatement(Cambiar_Estado);
        preparedStatement.setInt(1,TemaId);
        preparedStatement.setInt(2,ProfesorId);
        preparedStatement.setInt(3,NivelId);
        preparedStatement.setInt(4,MateriaId);
        //System.out.println("Establecemos conexion y preparamos la consulta");
        preparedStatement.executeUpdate();
        return "redirect:/VerTemas/"+MateriaId+"/"+ProfesorId+"/"+NivelId;
    }

    public String DesactivarTema(HttpServletRequest request, Model model, int MateriaId, int TemaId, int ProfesorId, int NivelId) throws SQLException {
        connection=dataSource.getConnection();
        String Cambiar_Estado="update temas t set EsActivo=false where (TemaId=? and profesorId=? and NivelId=? and MateriaId=?)";
        PreparedStatement preparedStatement=connection.prepareStatement(Cambiar_Estado);
        preparedStatement.setInt(1,TemaId);
        preparedStatement.setInt(2,ProfesorId);
        preparedStatement.setInt(3,NivelId);
        preparedStatement.setInt(4,MateriaId);
        //System.out.println("Establecemos conexion y preparamos la consulta");
        preparedStatement.executeUpdate();
        return "redirect:/VerTemas/"+MateriaId+"/"+ProfesorId+"/"+NivelId;
    }

    public String CrearPunto(HttpServletRequest request,Model model,int TemaId){
        temas.setTemaId(TemaId);
        return "NuevoPunto";
    }
    public String RegistrarPunto(HttpServletRequest request,Model model) throws  SQLException {
        connection=dataSource.getConnection();
        String Agregar_Punto="insert into puntos values (null,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(Agregar_Punto);
        preparedStatement.setInt(1,temas.getTemaId());
        preparedStatement.setString(2,request.getParameter("point_Name"));
        preparedStatement.setString(3,request.getParameter("point_Resumen"));
        preparedStatement.setString(4,request.getParameter("point_text"));
        preparedStatement.setInt(5, Integer.parseInt(request.getParameter("point_order")));
        preparedStatement.setInt(6, Integer.parseInt(request.getParameter("point_Number")));
        preparedStatement.setBoolean(7, Boolean.parseBoolean(request.getParameter("activar_Punto")));
        preparedStatement.execute();

        return "NuevoPunto";
    }

    public String OcultarPunto(HttpServletRequest request,Model model,int TemaId,int PuntoId) throws  SQLException{
        connection=dataSource.getConnection();
        String Cambiar_Estado="update puntos set EsActivo=false where TemaId=? and PuntoId=?";
        PreparedStatement preparedStatement=connection.prepareStatement(Cambiar_Estado);
        preparedStatement.setInt(1,TemaId);
        preparedStatement.setInt(2,PuntoId);
        preparedStatement.executeUpdate();
        return "redirect:/Puntos_Tema/"+TemaId;
    }
    public String VisualizarPunto(HttpServletRequest request,Model model,int TemaId,int PuntoId)throws SQLException{
        connection=dataSource.getConnection();
        String Cambiar_Estado="update puntos set EsActivo=true where TemaId=? and PuntoId=?";
        PreparedStatement preparedStatement=connection.prepareStatement(Cambiar_Estado);
        preparedStatement.setInt(1,TemaId);
        preparedStatement.setInt(2,PuntoId);
        preparedStatement.executeUpdate();
        //System.out.println("Ejecutamos la actualización");
        return "redirect:/Puntos_Tema/"+TemaId;
    }

    public String CrearCuestionario(HttpServletRequest request,Model model){
        return "Cuestionario";
    }
}
