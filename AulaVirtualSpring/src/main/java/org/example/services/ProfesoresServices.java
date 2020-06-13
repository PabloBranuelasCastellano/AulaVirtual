package org.example.services;

import org.example.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

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
    Cuestionarios cuestionarios;
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
        model.addAttribute("Grupos_Materia", gruposMateria);
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
        return ExamenesProfesor(request, model);

    }

    public String ExamenesProfesor(HttpServletRequest request,Model model)throws  SQLException{

        connection=dataSource.getConnection();
        String Ver_Examenes="select c.CuestionarioId,c.Titulo , c.Instrucciones , c.Resumen ,c.NumeroPreguntasPorTest , c.PuntosAcierto , c.PuntosError from cuestionarios c,profesores p where(c.ProfesorId =p.ProfesorId and c.ProfesorId =?)";
        PreparedStatement preparedStatement=connection.prepareStatement(Ver_Examenes);
        preparedStatement.setInt(1,profesores.getIdProfesor());
        ArrayList<Cuestionarios>cuestionariosArrayList=new ArrayList<>();
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            cuestionarios=new Cuestionarios();
            cuestionarios.setProfesorId(profesores.getIdProfesor());
            cuestionarios.setExamenId(resultSet.getInt("CuestionarioId"));
            cuestionarios.setNombre_Examen(resultSet.getString("Titulo"));
            cuestionarios.setInstrucciones_Examen(resultSet.getString("Instrucciones"));
            cuestionarios.setResumen_Examen(resultSet.getString("Resumen"));
            cuestionarios.setNum_Preguntas(resultSet.getInt("NumeroPreguntasPorTest"));
            cuestionarios.setPuntosAcierto(resultSet.getInt("PuntosAcierto"));
            cuestionarios.setPuntosError(resultSet.getInt("PuntosError"));
            cuestionariosArrayList.add(cuestionarios);

        }
        model.addAttribute("Cuestionarios_Profesor",cuestionariosArrayList);

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
        preparedStatement.execute();
        return "NuevoTema";
    }

    public String TemasProfesor(HttpServletRequest request, Model model, int ProfesorId, int MateriaId, int NivelId) throws SQLException {

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

    public String CrearPunto(HttpServletRequest request, Model model, int TemaId){

        temas.setTemaId(TemaId);
        return "NuevoPunto";
    }
    
    public String RegistrarPunto(HttpServletRequest request, Model model) throws  SQLException {

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

    public String OcultarPunto(HttpServletRequest request, Model model, int TemaId, int PuntoId) throws  SQLException{

        connection=dataSource.getConnection();
        String Cambiar_Estado="update puntos set EsActivo=false where TemaId=? and PuntoId=?";
        PreparedStatement preparedStatement=connection.prepareStatement(Cambiar_Estado);
        preparedStatement.setInt(1,TemaId);
        preparedStatement.setInt(2,PuntoId);
        preparedStatement.executeUpdate();
        return "redirect:/Puntos_Tema/"+TemaId;
    }

    public String VisualizarPunto(HttpServletRequest request, Model model, int TemaId, int PuntoId)throws SQLException{

        connection=dataSource.getConnection();
        String Cambiar_Estado="update puntos set EsActivo=true where TemaId=? and PuntoId=?";
        PreparedStatement preparedStatement=connection.prepareStatement(Cambiar_Estado);
        preparedStatement.setInt(1,TemaId);
        preparedStatement.setInt(2,PuntoId);
        preparedStatement.executeUpdate();
        return "redirect:/Puntos_Tema/"+TemaId;
    }

    public String CrearCuestionario(HttpServletRequest request, Model model){

        return "Cuestionario";
    }

    public String DatosCuestionario(HttpServletRequest request, Model model)throws SQLException{

        LocalDate localDate = LocalDate.now();
        connection=dataSource.getConnection();
        String NuevoExamen="insert into cuestionarios values(null,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(NuevoExamen);
        preparedStatement.setInt(1,profesores.getIdProfesor());
        preparedStatement.setString(2,request.getParameter("Title_Examen"));
        preparedStatement.setString(3,request.getParameter("Instrucciones_Examen"));
        preparedStatement.setString(4,request.getParameter("Resumen_Examen"));
        preparedStatement.setInt(5, Integer.parseInt(request.getParameter("Num_Preguntas")));
        preparedStatement.setString(6, request.getParameter("Punto_Correcto"));
        preparedStatement.setString(7, request.getParameter("PuntoIncorrecto"));
        preparedStatement.setString(8,localDate.toString());
        preparedStatement.setBoolean(9, Boolean.parseBoolean(request.getParameter("activar_Examen")));
        preparedStatement.execute();
        return "Cuestionario";
    }

    public String CuestionariosInactivos(HttpServletRequest request,Model model)throws SQLException{
        System.out.println("El numero de Preguntas es  "+cuestionarios.getNum_Preguntas());
        
        return null;

    }

    public String ListaCuestionarios(HttpServletRequest request, Model model, int examenId, String nombreExamen) throws SQLException {

        connection=dataSource.getConnection();
        String numPreguntas="select NumeroPreguntasPorTest from cuestionarios  where CuestionarioId=?";
        PreparedStatement preparedStatement=connection.prepareStatement(numPreguntas);
        preparedStatement.setInt(1,examenId);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()) {
            cuestionarios.setExamenId(examenId);
            cuestionarios.setNum_Preguntas(resultSet.getInt("NumeroPreguntasPorTest"));
            cuestionarios.setNombre_Examen(nombreExamen);
        }
        System.out.println("El id del examen es "+examenId+" y el numero de preguntas es "+cuestionarios.getNum_Preguntas()+
                " y el nombre del examen es "+nombreExamen);
        model.addAttribute("Numero_Preguntas",cuestionarios.getNum_Preguntas());
        /*request.getSession().setAttribute("Num_Cuestionario",examenId);
        request.getSession().setAttribute("Num_Pregunta",cuestionarios.getNum_Preguntas());*/
        return "Preguntas";
    }

    public String CrearPreguntas(HttpServletRequest request, Model model) throws SQLException{
        LocalDate localDate = LocalDate.now();
        connection=dataSource.getConnection();
        System.out.println("El numero del cuestionario es "+cuestionarios.getExamenId() +"y el numero de pregubtas que tiene es "
                +cuestionarios.getNum_Preguntas()+ "y el nombre del examen es "+ cuestionarios.getNombre_Examen());
        String guardar_preguntas="insert into Preguntas values(null,?,?,?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(guardar_preguntas);
        for(int i=1;i<cuestionarios.getNum_Preguntas()+1;i++){
            preparedStatement.setInt(1,cuestionarios.getExamenId());
            preparedStatement.setString(2,request.getParameter("Pregunta_"+i));
            preparedStatement.setString(3,localDate.toString());
            //preparedStatement.setInt(4, Integer.parseInt(request.getParameter("orderPregunta_"+i)));
           System.out.println(request.getParameter("orderPregunta_"+i));
            preparedStatement.setBoolean(5, Boolean.parseBoolean(request.getParameter("activar_Pregunta_"+i)));
            //preparedStatement.execute();
        }
        model.addAttribute("Numero_Preguntas",cuestionarios.getNum_Preguntas());
        return "Preguntas";
    }
}
