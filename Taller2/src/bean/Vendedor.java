package bean;

import java.util.List;

public class Vendedor {
    private int cedula;
    private String nombres;
    private String apellidos;
    private int anosExperiencia;
    private int edad;
    private List<String> eps;

    public Vendedor(int cedula, String nombres, String apellidos, int anosExperiencia, int edad, List<String> eps) {
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

    public List<String> getEps() {
        return eps;
    }

    public void setEps(List<String> eps) {
        this.eps = eps;
    }
}

