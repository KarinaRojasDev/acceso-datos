package packageEscrituraJSON;


import java.util.ArrayList;

public class Titulo {
    private String nombre;

    private ArrayList<Heroe>heroes;

    public Titulo(String nombre) {
        this.nombre = nombre;
        heroes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean add(Heroe heroe) {
        return heroes.add(heroe);
    }
    public ArrayList<Heroe> getHeroes() {
        return heroes;
    }
}
