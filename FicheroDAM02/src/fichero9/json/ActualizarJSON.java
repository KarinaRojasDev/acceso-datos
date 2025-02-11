package fichero9.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ActualizarJSON {
    public static void main(String[] args) {

        ActualizarJSON actualizar = new ActualizarJSON();

        actualizar.actualizarJSON("fichero.json");



    }
    public void actualizarJSON (String nombreArchivo){

        try {
            FileReader fileReader = new FileReader(nombreArchivo);
            StringBuilder builder = new StringBuilder();

            int caracter;
            while((caracter = fileReader.read()) != -1){
                builder.append((char) caracter);
            }
            fileReader.close();


            JSONObject moduloJSON = new JSONObject(builder.toString());
            JSONArray arrayJSON = moduloJSON.getJSONArray("curso");

            for(int i=0; i<arrayJSON.length(); i++){
                JSONObject modulo = arrayJSON.getJSONObject(i);

                String nombre = modulo.getString("nombre");
                int horas = modulo.getInt("horas");
                double nota = modulo.getDouble("nota");

                horas++;

                modulo.put("nombre",nombre);
                modulo.put("nota",nota);

                modulo.put("horas",horas);
            }

            FileWriter fw = new FileWriter(nombreArchivo);
            fw.write(moduloJSON.toString(4));
            fw.close();
            System.out.println("Datos actualizados y guardados en el archivo JSON");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
