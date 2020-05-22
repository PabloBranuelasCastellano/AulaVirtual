package org.example.Entities;

public class Grupos {
    private  int idGrupo;
    private  String NombreMateria;
    private  String NivelGrpo;
    private  int ProfesorGrupo;
    private String NombreGrupo;
    private int CursoAcademicoGrupo;

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

    public String getNivelGrpo() {
        return NivelGrpo;
    }

    public void setNivelGrpo(String nivelGrpo) {
        NivelGrpo = nivelGrpo;
    }

    public int getProfesorGrupo() {
        return ProfesorGrupo;
    }

    public void setProfesorGrupo(int profesorGrupo) {
        ProfesorGrupo = profesorGrupo;
    }

    public String getNombreGrupo() {
        return NombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        NombreGrupo = nombreGrupo;
    }

    public int getCursoAcademicoGrupo() {
        return CursoAcademicoGrupo;
    }

    public void setCursoAcademicoGrupo(int cursoAcademicoGrupo) {
        CursoAcademicoGrupo = cursoAcademicoGrupo;
    }
}
