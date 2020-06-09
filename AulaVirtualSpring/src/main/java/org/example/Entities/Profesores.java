package org.example.Entities;

import javax.persistence.Entity;

@Entity
public class Profesores {
    private int IdProfesor;
    private int IdCentro;
    private String NombreProfesor;
    private String PrimerApellidoProfesor;
    private String SegundoApellidoProfesor;
    private String NIF_NIEProfesor;
    private String FechaAltaProfesor;
    private String UsuarioProfesor;
    private String EmailProfesor;
    private String ClaveProfesor;
    private boolean EsActivoProfesor;

    public int getIdProfesor() {
        return IdProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        IdProfesor = idProfesor;
    }

    public int getIdCentro() {
        return IdCentro;
    }

    public void setIdCentro(int idCentro) {
        IdCentro = idCentro;
    }

    public String getNombreProfesor() {
        return NombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        NombreProfesor = nombreProfesor;
    }

    public String getPrimerApellidoProfesor() {
        return PrimerApellidoProfesor;
    }

    public void setPrimerApellidoProfesor(String primerApellidoProfesor) {
        PrimerApellidoProfesor = primerApellidoProfesor;
    }

    public String getSegundoApellidoProfesor() {
        return SegundoApellidoProfesor;
    }

    public void setSegundoApellidoProfesor(String segundoApellidoProfesor) {
        SegundoApellidoProfesor = segundoApellidoProfesor;
    }

    public String getNIF_NIEProfesor() {
        return NIF_NIEProfesor;
    }

    public void setNIF_NIEProfesor(String NIF_NIEProfesor) {
        this.NIF_NIEProfesor = NIF_NIEProfesor;
    }

    public String getFechaAltaProfesor() {
        return FechaAltaProfesor;
    }

    public void setFechaAltaProfesor(String fechaAltaProfesor) {
        FechaAltaProfesor = fechaAltaProfesor;
    }

    public String getUsuarioProfesor() {
        return UsuarioProfesor;
    }

    public void setUsuarioProfesor(String usuarioProfesor) {
        UsuarioProfesor = usuarioProfesor;
    }

    public String getEmailProfesor() {
        return EmailProfesor;
    }

    public void setEmailProfesor(String emailProfesor) {
        EmailProfesor = emailProfesor;
    }

    public String getClaveProfesor() {
        return ClaveProfesor;
    }

    public void setClaveProfesor(String claveProfesor) {
        ClaveProfesor = claveProfesor;
    }

    public boolean isEsActivoProfesor() {
        return EsActivoProfesor;
    }

    public void setEsActivoProfesor(boolean esActivoProfesor) {
        EsActivoProfesor = esActivoProfesor;
    }
}
