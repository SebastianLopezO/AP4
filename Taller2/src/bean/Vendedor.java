package bean;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    private int cedula;
    private String nombres;
    private String apellidos;
    private int anosExperiencia;
    private int edad;
    private String eps;

    public Vendedor(int cedula, String nombres, String apellidos, int anosExperiencia, int edad, String eps) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.anosExperiencia = anosExperiencia;
        this.edad = edad;
        this.eps = eps;
    }

    // Getters y Setters
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }
}

