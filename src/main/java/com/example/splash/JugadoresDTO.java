package com.example.splash;

public class JugadoresDTO {
    private int Id;
    private String Pais;
    private String Region;
    private String Capitan;
    private String Ranking;
    private String Mundiales_Ganados;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getCapitan() {
        return Capitan;
    }

    public void setCapitan(String capitan) {
        Capitan = capitan;
    }

    public String getRanking() {
        return Ranking;
    }

    public void setRanking(String ranking) {
        Ranking = ranking;
    }

    public String getMundiales_Ganados() {
        return Mundiales_Ganados;
    }

    public void setMundiales_Ganados(String mundiales_Ganados) {
        Mundiales_Ganados = mundiales_Ganados;
    }
}
