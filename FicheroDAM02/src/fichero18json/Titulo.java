package fichero18json;

import java.util.ArrayList;

public class Titulo {
    private String nombre;
    private ArrayList<Participante> listaParticipantes;

    public Titulo(String nombre) {
        this.nombre = nombre;
        listaParticipantes = new ArrayList<>();
    }

    public ArrayList<Participante> getListaParticipantes() {
        return listaParticipantes;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean add(Participante participante) {
        return listaParticipantes.add(participante);
    }

    public Participante remove(int index) {
        return listaParticipantes.remove(index);
    }

    public int size() {
        return listaParticipantes.size();
    }
}
