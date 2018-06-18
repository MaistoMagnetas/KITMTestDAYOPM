package com.example.moksleivis.kitmtestodienaopm;

public class User {
    private int id;
    private String vardasPavarde;
    private String gimimoData;
    private String telNr;
    private String klientoTipas;

    public User(int id, String vardasPavarde, String gimimoData, String telNr, String klientoTipas) {
        this.id = id;
        this.vardasPavarde = vardasPavarde;
        this.gimimoData = gimimoData;
        this.telNr = telNr;
        this.klientoTipas = klientoTipas;
    }

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVardasPavarde() {
        return vardasPavarde;
    }

    public void setVardasPavarde(String vardasPavarde) {
        this.vardasPavarde = vardasPavarde;
    }

    public String getGimimoData() {
        return gimimoData;
    }

    public void setGimimoData(String gimimoData) {
        this.gimimoData = gimimoData;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public String getKlientoTipas() {
        return klientoTipas;
    }

    public void setKlientoTipas(String klientoTipas) {
        this.klientoTipas = klientoTipas;
    }
}
