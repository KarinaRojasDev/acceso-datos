package fichero18json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Titulo titulo = new Titulo("Copa de Europa 24/25");
        Participante participante1 = new Participante("Jude Bellingham",25);
        Participante participante2 = new Participante("Kyllian Mbappé",25);
        titulo.add(participante1);
        titulo.add(participante2);

        JSONObject jsonParticipantes = new JSONObject();
        jsonParticipantes.put("Nombre",titulo.getNombre());

        JSONArray jsonArrayParticipantes = new JSONArray();

        for(Participante pLista: titulo.getListaParticipantes()){
            JSONObject listaParticipantesJSON = new JSONObject();
            listaParticipantesJSON.put("nombre",pLista.getNombre());
            listaParticipantesJSON.put("edad",pLista.getEdad());

            jsonArrayParticipantes.put(listaParticipantesJSON);
        }
        //Nombre del array OJO
        jsonParticipantes.put("participantes",jsonArrayParticipantes);

        try {

            FileWriter fw = new FileWriter("archivo.json");
            fw.write(jsonParticipantes.toString(4));
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
