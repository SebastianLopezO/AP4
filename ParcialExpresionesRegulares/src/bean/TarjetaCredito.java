package bean;

public class TarjetaCredito {
    private String Tipo;
    private String Numero;
    private String Fecha;
    private String Nombre;
    private String Apellido;
    private String Codigo;

    public TarjetaCredito(String tipo, String numero, String fecha, String nombre, String apellido, String codigo) {
        Tipo = tipo;
        Numero = numero;
        Fecha = fecha;
        Nombre = nombre;
        Apellido = apellido;
        Codigo = codigo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

}
