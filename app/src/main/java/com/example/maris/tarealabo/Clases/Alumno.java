package com.example.maris.tarealabo.Clases;

public class Alumno {

    private String nombre;
    private String carnet;
    private String nota;

    public Alumno(){}

    public Alumno(String nombre, String carnet, String nota) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }
    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNota() {
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
}
