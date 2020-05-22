package org.example.Entities;

public class GruposAlumno {
    private int IdAlumno;
    private String nombreAlumno;
    private String primerApellidoAlumno;
    private String segundoApellidoAlumno;

    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        IdAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getPrimerApellidoAlumno() {
        return primerApellidoAlumno;
    }

    public void setPrimerApellidoAlumno(String primerApellidoAlumno) {
        this.primerApellidoAlumno = primerApellidoAlumno;
    }

    public String getSegundoApellidoAlumno() {
        return segundoApellidoAlumno;
    }

    public void setSegundoApellidoAlumno(String segundoApellidoAlumno) {
        this.segundoApellidoAlumno = segundoApellidoAlumno;
    }
}
