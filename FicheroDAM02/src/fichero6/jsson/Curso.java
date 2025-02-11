package fichero6.jsson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    public static void main(String[] args) {

        List<Modulo> listaModulo = new ArrayList<>();

        listaModulo.add(new Modulo("Mate",40,9.5));
        listaModulo.add(new Modulo("Historia",30,8.5));
        listaModulo.add(new Modulo("Biología",25,9.5));

        JSONObject curso = new JSONObject();
        JSONArray jsarray = new JSONArray();

        for(Modulo modulo: listaModulo){
            JSONObject moduloJSON = new JSONObject();

            moduloJSON.put("nombre",modulo.getNombre());
            moduloJSON.put("horas",modulo.getHoras());
            moduloJSON.put("nota",modulo.getNota());

            jsarray.put(moduloJSON); //array y json juntos
        }

        curso.put("curso",jsarray);

        try {

            FileWriter fw = new FileWriter("curso.json");
            fw.write(curso.toString(4));
            fw.close();

            System.out.println("Archivo JSON creado correctamente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
