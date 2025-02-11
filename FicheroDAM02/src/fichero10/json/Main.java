package fichero10.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();


        main.creacionArchivoJSON("Archivo.json");
        main.actualizarJSON("Archivo.json");



    }
    public void creacionArchivoJSON(String nombreArchivo){

        File fichero = new File(nombreArchivo);

        if(!fichero.exists()) {
            JSONObject tarea = new JSONObject();
            JSONArray arrayJSON = new JSONArray();

            JSONObject tarea1 = new JSONObject();
            tarea1.put("titulo", "Desarrollo de interzas");
            tarea1.put("responsable", "Juan Perez");
            tarea1.put("duracion", 40);
            tarea1.put("prioridad", "Alta");

            JSONObject tarea2 = new JSONObject();
            tarea2.put("titulo", "Pruebas de calidad");
            tarea2.put("responsable", "Maria López");
            tarea2.put("duracion", 30);
            tarea2.put("prioridad", "Media");

            JSONObject tarea3 = new JSONObject();
            tarea3.put("titulo", "Documentación");
            tarea3.put("responsable", "Lucas García");
            tarea3.put("duracion", 20);
            tarea3.put("prioridad", "Baja");

            arrayJSON.put(tarea1);
            arrayJSON.put(tarea2);
            arrayJSON.put(tarea3);

            tarea.put("proyecto", arrayJSON);

            try {
                FileWriter fw = new FileWriter(nombreArchivo);
                fw.write(tarea.toString(4)); // 4 espacios de indentación
                fw.close();
                System.out.println("Archivo JSON de ejemplo creado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo JSON: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo JSON ya existe.");

        }
    }
    public void actualizarJSON(String nombreArchivo){

        try {
            FileReader fr = new FileReader(nombreArchivo);
            StringBuilder builder = new StringBuilder();

            int caracter;
            while((caracter = fr.read()) !=-1){
                builder.append((char)caracter);
            }
            fr.close();

            JSONObject tareaJSON = new JSONObject(builder.toString());

            // Verificar si la clave "proyecto" existe en el JSON
            if (tareaJSON.has("proyecto")) {
                JSONArray arrayJS = tareaJSON.getJSONArray("proyecto");

                for(int i=0; i<arrayJS.length();i++){
                    JSONObject tarea = arrayJS.getJSONObject(i);

                    String titulo = tarea.getString("titulo");
                    String responsable = tarea.getString("responsable");
                    int duracion = tarea.getInt("duracion");
                    String prioridad = tarea.getString("prioridad");

                    duracion++;
                    tarea.put("duracion",duracion);
                }
                FileWriter fw = new FileWriter(nombreArchivo);
                fw.write(tareaJSON.toString(4));
                fw.close();
                System.out.println("Archivo JSON actualizado correctamente");
            }else {
                System.out.println("La clave 'proyecto' no se encuentra en el archivo JSON.");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
