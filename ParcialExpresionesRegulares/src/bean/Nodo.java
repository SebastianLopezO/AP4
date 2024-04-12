package bean;

public class Nodo {
    private String dato1;
    private String dato2;
    private String codigo;

    public Nodo(String dato1, String dato2, String codigo) {
        this.dato1 = dato1;
        this.dato2 = dato2;
        this.codigo = codigo;
    }

    public Nodo() {

    }

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
