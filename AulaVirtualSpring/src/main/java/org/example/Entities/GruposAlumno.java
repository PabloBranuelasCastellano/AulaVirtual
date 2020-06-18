package org.example.Entities;


public class GruposAlumno {
    private int IdAlumno;
    private String nombreAlumno;
    private String primerApellidoAlumno;
    private String segundoApellidoAlumno;
    private int GrupoId;
    private int MateriaId;
    private String MateriaNombre;

    public int getGrupoId() {
        return GrupoId;
    }

    public void setGrupoId(int grupoId) {
        GrupoId = grupoId;
    }

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

    @Override
    public String toString() {
        return "GruposAlumno{" +
                "IdAlumno=" + IdAlumno +
                ", nombreAlumno='" + nombreAlumno + '\'' +
                ", primerApellidoAlumno='" + primerApellidoAlumno + '\'' +
                ", segundoApellidoAlumno='" + segundoApellidoAlumno + '\'' +
                ", GrupoId=" + GrupoId +
                ", MateriaId=" + MateriaId +
                ", MateriaNombre='" + MateriaNombre + '\'' +
                "}\n";
    }
}
