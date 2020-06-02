package org.example.Entities;


public class Temas {
    private int TemaId;
    private String TituloTema;
    private String ResumenTema;
    private int ProfesorId;
    private int MateriaId;
    private int NivelId;
    private boolean TemaActivo;

    public int getTemaId() {
        return TemaId;
    }

    public void setTemaId(int temaId) {
        TemaId = temaId;
    }

    public String getTituloTema() {
        return TituloTema;
    }

    public void setTituloTema(String tituloTema) {
        TituloTema = tituloTema;
    }

    public String getResumenTema() {
        return ResumenTema;
    }

    public void setResumenTema(String resumenTema) {
        ResumenTema = resumenTema;
    }

    public int getProfesorId() {
        return ProfesorId;
    }

    public void setProfesorId(int profesorId) {
        ProfesorId = profesorId;
    }

    public int getMateriaId() {
        return MateriaId;
    }

    public void setMateriaId(int materiaId) {
        MateriaId = materiaId;
    }

    public int getNivelId() {
        return NivelId;
    }

    public void setNivelId(int nivelId) {
        NivelId = nivelId;
    }

    public boolean isTemaActivo() {
        return TemaActivo;
    }

    public void setTemaActivo(boolean temaActivo) {
        TemaActivo = temaActivo;
    }
}
