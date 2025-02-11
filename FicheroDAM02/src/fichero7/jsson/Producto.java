package fichero7.jsson;

public class Producto {
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private double tamanoPantalla;

    public Producto(String marca, String modelo, String sistemaOperativo) {
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
    }

    public Producto(String marca, String modelo, String sistemaOperativo,double tamanoPantalla){
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.tamanoPantalla = tamanoPantalla;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public double getTamanoPantalla() {
        return tamanoPantalla;
    }
}
