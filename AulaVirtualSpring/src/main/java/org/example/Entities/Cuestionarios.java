package org.example.Entities;

public class Cuestionarios {
    private int ExamenId;
    private int ProfesorId;
    private String Nombre_Examen;
    private String Resumen_Examen;
    private String Instrucciones_Examen;
    private float PuntosAcierto;
    private float PuntosError;
    private int Num_Preguntas;

    public int getExamenId() {
        return ExamenId;
    }

    public void setExamenId(int examenId) {
        ExamenId = examenId;
    }

    public int getProfesorId() {
        return ProfesorId;
    }

    public void setProfesorId(int profesorId) {
        ProfesorId = profesorId;
    }

    public String getNombre_Examen() {
        return Nombre_Examen;
    }

    public void setNombre_Examen(String nombre_Examen) {
        Nombre_Examen = nombre_Examen;
    }

    public String getResumen_Examen() {
        return Resumen_Examen;
    }

    public void setResumen_Examen(String resumen_Examen) {
        Resumen_Examen = resumen_Examen;
    }

    public String getInstrucciones_Examen() {
        return Instrucciones_Examen;
    }

    public void setInstrucciones_Examen(String instrucciones_Examen) {
        Instrucciones_Examen = instrucciones_Examen;
    }

    public float getPuntosAcierto() {
        return PuntosAcierto;
    }

    public void setPuntosAcierto(float puntosAcierto) {
        PuntosAcierto = puntosAcierto;
    }

    public float getPuntosError() {
        return PuntosError;
    }

    public void setPuntosError(float puntosError) {
        PuntosError = puntosError;
    }

    public int getNum_Preguntas() {
        return Num_Preguntas;
    }

    public void setNum_Preguntas(int num_Preguntas) {
        Num_Preguntas = num_Preguntas;
    }
}
