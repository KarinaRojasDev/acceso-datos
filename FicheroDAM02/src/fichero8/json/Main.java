package fichero8.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Bebida> bebidaList = new ArrayList<>();
        bebidaList.add(new Bebida("L45",6,null));  //archivos nulos y su condición
        bebidaList.add(new Bebida("Leche",8));

        JSONObject bebidas = new JSONObject();
        JSONArray jsonArray = new JSONArray();


        // Mediante un for creamos un bucle y un Objeto JSON y
        // verificamos si hay valores antes de agregarlos a nuestro archivo JSON

        for(Bebida bebida: bebidaList){
            JSONObject bebidaJSON = new JSONObject();
            if(bebida.getCodigo() != null && !bebida.getCodigo().equals("N/A")){
                bebidaJSON.put("codigo",bebida.getCodigo());
            }
            bebidaJSON.put("precio",bebida.getPrecio());
            if(bebida.getNombre() !=null && !bebida.getNombre().equals("N/A")){
                bebidaJSON.put("nombre",bebida.getNombre());
            }
            jsonArray.put(bebidaJSON);
        }
        bebidas.put("bebidas",jsonArray);

        try {

            FileWriter fileWriter = new FileWriter("bebidas.json");
            fileWriter.write(bebidas.toString(4));

            fileWriter.close();
            System.out.println("Archivo JSON creado correctamente");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
