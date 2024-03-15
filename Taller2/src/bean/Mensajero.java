package bean;

import java.util.List;

public class Mensajero {
    private int cedula;
    private String nombres;
    private String apellidos;
    private int edad;
    private List<String> eps;
    private List<String> arl;
    private List<String> pension;

    public Mensajero(int cedula, String nombres, String apellidos, int edad, List<String> eps, List<String> arl, List<String> pension) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.eps = eps;
        this.arl = arl;
        this.pension = pension;
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

    public List<String> getArl() {
        return arl;
    }

    public void setArl(List<String> arl) {
        this.arl = arl;
    }

    public List<String> getPension() {
        return pension;
    }

    public void setPension(List<String> pension) {
        this.pension = pension;
    }
}

