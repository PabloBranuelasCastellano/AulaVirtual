package org.example.Entities;


public class Materias {
    private int MateriaId;
    private String NombreMateria;
    private int NivelId;
    private int ProfesorId;


    public int getMateriaId() {
        return MateriaId;
    }

    public void setMateriaId(int materiaId) {
        MateriaId = materiaId;
    }

    public String getNombreMateria() {
        return NombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        NombreMateria = nombreMateria;
    }

    public int getNivelId() {
        return NivelId;
    }

    public void setNivelId(int nivelId) {
        NivelId = nivelId;
    }

    public int getProfesorId() {
        return ProfesorId;
    }

    public void setProfesorId(int profesorId) {
        ProfesorId = profesorId;
    }
}
