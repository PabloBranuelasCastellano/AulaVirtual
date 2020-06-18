package org.example.Entities;

public class CuestionariosAlumnos {
    private int IdCuestionarioAlumnos;
    private String TituloCuestionario;
    private int GrupoAlumno;
    private int IdAlumno;

    public int getIdCuestionarioAlumnos() {
        return IdCuestionarioAlumnos;
    }

    public void setIdCuestionarioAlumnos(int idCuestionarioAlumnos) {
        IdCuestionarioAlumnos = idCuestionarioAlumnos;
    }

    public String getTituloCuestionario() {
        return TituloCuestionario;
    }

    public void setTituloCuestionario(String tituloCuestionario) {
        TituloCuestionario = tituloCuestionario;
    }

    public int getGrupoAlumno() {
        return GrupoAlumno;
    }

    public void setGrupoAlumno(int grupoAlumno) {
        GrupoAlumno = grupoAlumno;
    }

    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        IdAlumno = idAlumno;
    }

    @Override
    public String toString() {
        return "CuestionariosAlumnos{" +
                "IdCuestionarioAlumnos=" + IdCuestionarioAlumnos +
                ", TituloCuestionario='" + TituloCuestionario + '\'' +
                ", GrupoAlumno=" + GrupoAlumno +
                ", IdAlumno=" + IdAlumno +
                "}\n";
    }
}
