package org.example.Entities;

public class Grupos {
    private  int idGrupo;
    private  String NombreMateria;
    private  int IdMateria;
    private  String NivelGrupo;
    private  String ProfesorGrupo;
    private String NombreGrupo;
    private String CursoAcademicoGrupo;

    public int getIdMateria() {
        return IdMateria;
    }

    public void setIdMateria(int idMateria) {
        IdMateria = idMateria;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreMateria() {
        return NombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        NombreMateria = nombreMateria;
    }

    public String getNivelGrupo() {
        return NivelGrupo;
    }

    public void setNivelGrupo(String nivelGrupo) {
        NivelGrupo = nivelGrupo;
    }

    public String getProfesorGrupo() {
        return ProfesorGrupo;
    }

    public void setProfesorGrupo(String profesorGrupo) {
        ProfesorGrupo = profesorGrupo;
    }

    public String getNombreGrupo() {
        return NombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        NombreGrupo = nombreGrupo;
    }

    public String getCursoAcademicoGrupo() {
        return CursoAcademicoGrupo;
    }

    public void setCursoAcademicoGrupo(String cursoAcademicoGrupo) {
        CursoAcademicoGrupo = cursoAcademicoGrupo;
    }
}
