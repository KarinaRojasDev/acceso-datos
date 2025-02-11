package fichero6.jsson;

public class Modulo {
    private String nombre;
    private int horas;
    private double nota;

    public Modulo(String nombre, int horas, double nota){
        this.nombre = nombre;
        this.horas = horas;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHoras() {
        return horas;
    }

    public double getNota() {
        return nota;
    }
}
