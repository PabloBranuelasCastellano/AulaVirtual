package org.example.Entities;

import javax.persistence.Entity;

@Entity
public class GruposAlumno {
    private int IdAlumno;
    private String nombreAlumno;
    private String primerApellidoAlumno;
    private String segundoApellidoAlumno;
    private int MateriaId;
    private String MateriaNombre;

    public int getMateriaId() {
        return MateriaId;
    }

    public void setMateriaId(int materiaId) {
        MateriaId = materiaId;
    }

    public String getMateriaNombre() {
        return MateriaNombre;
    }

    public void setMateriaNombre(String materiaNombre) {
        MateriaNombre = materiaNombre;
    }

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
