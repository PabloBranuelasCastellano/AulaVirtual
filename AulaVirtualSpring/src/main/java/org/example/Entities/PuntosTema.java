package org.example.Entities;

public class PuntosTema {
    private int IdPunto;
    private int TemaId;
    private String TituloPunto;
    private String ResumenPunto;
    private String TextoPunto;

    public int getIdPunto() {
        return IdPunto;
    }

    public void setIdPunto(int idPunto) {
        IdPunto = idPunto;
    }

    public int getTemaId() {
        return TemaId;
    }

    public void setTemaId(int temaId) {
        TemaId = temaId;
    }

    public String getTituloPunto() {
        return TituloPunto;
    }

    public void setTituloPunto(String tituloPunto) {
        TituloPunto = tituloPunto;
    }

    public String getResumenPunto() {
        return ResumenPunto;
    }

    public void setResumenPunto(String resumenPunto) {
        ResumenPunto = resumenPunto;
    }

    public String getTextoPunto() {
        return TextoPunto;
    }

    public void setTextoPunto(String textoPunto) {
        TextoPunto = textoPunto;
    }
}
