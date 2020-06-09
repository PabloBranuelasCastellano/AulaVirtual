package org.example.Entities;

import javax.persistence.Entity;

@Entity
public class Alumnos {
    private int IdAlumno;
    private int IdCentro;
    private String NombreAlumno;
    private String PrimerApellidoAlumno;
    private String SegundoApellidoAlumno;
    private String NIF_NIEAlumno;
    private String FechaAltaAlumno;
    private String UsuarioAlumno;
    private String EmailAlumno;
    private String ClaveAlumno;
    private boolean EsActivoAlumno;

    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        IdAlumno = idAlumno;
    }

    public int getIdCentro() {
        return IdCentro;
    }

    public void setIdCentro(int idCentro) {
        IdCentro = idCentro;
    }

    public String getNombreAlumno() {
        return NombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        NombreAlumno = nombreAlumno;
    }

    public String getPrimerApellidoAlumno() {
        return PrimerApellidoAlumno;
    }

    public void setPrimerApellidoAlumno(String primerApellidoAlumno) {
        PrimerApellidoAlumno = primerApellidoAlumno;
    }

    public String getSegundoApellidoAlumno() {
        return SegundoApellidoAlumno;
    }

    public void setSegundoApellidoAlumno(String segundoApellidoAlumno) {
        SegundoApellidoAlumno = segundoApellidoAlumno;
    }

    public String getNIF_NIEAlumno() {
        return NIF_NIEAlumno;
    }

    public void setNIF_NIEAlumno(String NIF_NIEAlumno) {
        this.NIF_NIEAlumno = NIF_NIEAlumno;
    }

    public String getFechaAltaAlumno() {
        return FechaAltaAlumno;
    }

    public void setFechaAltaAlumno(String fechaAltaAlumno) {
        FechaAltaAlumno = fechaAltaAlumno;
    }

    public String getUsuarioAlumno() {
        return UsuarioAlumno;
    }

    public void setUsuarioAlumno(String usuarioAlumno) {
        UsuarioAlumno = usuarioAlumno;
    }

    public String getEmailAlumno() {
        return EmailAlumno;
    }

    public void setEmailAlumno(String emailAlumno) {
        EmailAlumno = emailAlumno;
    }

    public String getClaveAlumno() {
        return ClaveAlumno;
    }

    public void setClaveAlumno(String claveAlumno) {
        ClaveAlumno = claveAlumno;
    }

    public boolean isEsActivoAlumno() {
        return EsActivoAlumno;
    }

    public void setEsActivoAlumno(boolean esActivoAlumno) {
        EsActivoAlumno = esActivoAlumno;
    }
}
