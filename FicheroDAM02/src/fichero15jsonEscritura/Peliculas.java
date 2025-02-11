package fichero15jsonEscritura;

public class Peliculas {

    private String titulo ;
    private int anioLanzamiento;
    private String director;
    private String genero;

    public Peliculas(String titulo, int anioLanzamiento,String director, String genero){
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.director = director;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }
}
