package fichero8.json;

public class Bebida {

    private String codigo;
    private int precio;
    private String nombre;

    public Bebida(String codigo, int precio,String nombre) {
        this.codigo = codigo;
        this.precio = precio;
        this.nombre = nombre;
    }

    public Bebida(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    public String getCodigo() {
        return codigo;
    }

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }
}
