package org.example.Entities;

public class Preguntas {
    private int PreguntaId;
    private int CuestionarioId;
    private String Fecha_Creacion;

    public int getPreguntaId() {
        return PreguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        PreguntaId = preguntaId;
    }

    public int getCuestionarioId() {
        return CuestionarioId;
    }

    public void setCuestionarioId(int cuestionarioId) {
        CuestionarioId = cuestionarioId;
    }

    public String getFecha_Creacion() {
        return Fecha_Creacion;
    }

    public void setFecha_Creacion(String fecha_Creacion) {
        Fecha_Creacion = fecha_Creacion;
    }
}
