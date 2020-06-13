package org.example.Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.example.services.LoginServices;
import org.example.services.ProfesoresServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class ProfesorController {


    @Autowired
    LoginServices loginServices;
    @Autowired
    ProfesoresServices profesoresServices;


    @GetMapping("/homeProfesores")
    public String PanelProfesor(HttpServletRequest request, Model model) throws SQLException {
        return profesoresServices.PanelProfesor(request, model);
    }


    @GetMapping("/VerAlumnos/{GrupoId}")
    public String AlumnosGrupos(HttpServletRequest request, Model model, @PathVariable int GrupoId) throws SQLException {
        return profesoresServices.AlumnosGrupos(request, model, GrupoId);
    }

    @GetMapping("/Gruposnoactivos")
    public String VerGruposDesactivados(HttpServletRequest request, Model model) throws SQLException {
        return profesoresServices.VerGruposDesactivados(request, model);
    }

    @GetMapping("/NuevoTema/{MateriaId}/{NivelId}/{ProfesorId}")
    public String CrearTema(HttpServletRequest request, Model model, @PathVariable int MateriaId, @PathVariable int NivelId, @PathVariable int ProfesorId) {
        /*System.out.println("El id de la Asignatura es " + MateriaId);
        System.out.println("El id del nivel es  " + NivelId);
        System.out.println("El id del Profesor es  " + ProfesorId);*/
        return profesoresServices.CrearTema(request, model, MateriaId, NivelId, ProfesorId);
    }

    @PostMapping("/NuevoTema")
    public String RegistrarTema(HttpServletRequest request, Model model) throws SQLException {

        return profesoresServices.RegistrarTema(request, model);
    }

    @GetMapping("/VerTemas/{ProfesorId}/{MateriaId}/{NivelId}")
    public String TemasProfesor(HttpServletRequest request, Model model,@PathVariable int ProfesorId, @PathVariable int MateriaId,@PathVariable int NivelId) throws SQLException {
        return profesoresServices.TemasProfesor(request, model, ProfesorId,MateriaId,NivelId);
    }

    @GetMapping("/Puntos_Tema/{IdTema}")
    public String Puntos_Tema(HttpServletRequest request, Model model, @PathVariable int IdTema) throws SQLException {
        return profesoresServices.Puntos_Tema(request, model, IdTema);
    }

    @GetMapping("/VisualizarTema/{MateriaId}/{TemaId}/{ProfesorId}/{NivelId}")
    public String ActivarTema(HttpServletRequest request, Model model, @PathVariable int MateriaId, @PathVariable int TemaId, @PathVariable int ProfesorId, @PathVariable int NivelId) throws SQLException {
        return profesoresServices.ActivarTema(request, model, MateriaId, TemaId, ProfesorId, NivelId);

    }

    @GetMapping("/OcultarTema/{MateriaId}/{TemaId}/{ProfesorId}/{NivelId}")
    public String DesactivarTema(HttpServletRequest request, Model model, @PathVariable int MateriaId, @PathVariable int TemaId, @PathVariable int ProfesorId, @PathVariable int NivelId) throws SQLException {
        return profesoresServices.DesactivarTema(request, model, MateriaId, TemaId, ProfesorId, NivelId);

    }

    @GetMapping("/AgregarPunto/{TemaId}")
    public String NuevoPunto(HttpServletRequest request,Model model,@PathVariable int TemaId){
        return profesoresServices.CrearPunto(request, model, TemaId);
    }

    @PostMapping("/AgregarPuntos")
    public String CrearPunto(HttpServletRequest request,Model model) throws  SQLException{
        return profesoresServices.RegistrarPunto(request, model);
    }

    @GetMapping("OcultarPunto/{TemaId}/{PuntoId}")
    public String OcultarPunto(HttpServletRequest request,Model model,@PathVariable int TemaId,@PathVariable int PuntoId)throws SQLException{
        return profesoresServices.OcultarPunto(request, model, TemaId, PuntoId);
    }

    @GetMapping("VisualizarPunto/{TemaId}/{PuntoId}")
    public String VisualizarPunto(HttpServletRequest request,Model model,@PathVariable int TemaId,@PathVariable int PuntoId)throws SQLException{
        return profesoresServices.VisualizarPunto(request, model, TemaId, PuntoId);
    }

    @GetMapping("NuevoCuestionario/")
    public String CrearCuestionario(HttpServletRequest request,Model model){
        return profesoresServices.CrearCuestionario(request,model);
    }

    @PostMapping("AgregarCuestionario/")
    public String DatosCuestionario(HttpServletRequest request,Model model)throws SQLException{
        return profesoresServices.DatosCuestionario(request,model);

    }

    @GetMapping("CuestionariosDesactivados/")
    public String CuestionariosInactivos(HttpServletRequest request,Model model)throws SQLException{
        return profesoresServices.CuestionariosInactivos(request,model);
    }

    @GetMapping("PreguntasCuestionario/{ExamenId}/{NombreExamen}")
    public String ListaCuestionarios(HttpServletRequest request,Model model,@PathVariable int ExamenId,@PathVariable String NombreExamen)throws SQLException{
        return profesoresServices.ListaCuestionarios(request,model,ExamenId,NombreExamen);
    }

    @PostMapping("AgregarPreguntas/")
    public String CrearPreguntas(HttpServletRequest request,Model model)throws SQLException{
        return profesoresServices.CrearPreguntas(request,model);
    }
}
