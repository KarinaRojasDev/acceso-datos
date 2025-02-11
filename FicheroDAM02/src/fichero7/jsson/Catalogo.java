package fichero7.jsson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    public static void main(String[] args) {

        List<Producto> productoList = new ArrayList<>();

        productoList.add(new Producto("Samsung","Galaxy S21","Android"));
        productoList.add(new Producto("Apple","MacBook Air","macOS",13.3));

        JSONObject catalogo = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Producto producto : productoList){
            JSONObject productoJSON = new JSONObject();

            productoJSON.put("marca",producto.getMarca());
            productoJSON.put("modelo",producto.getModelo());
            productoJSON.put("sistemaOperativo",producto.getSistemaOperativo());

            //SI EL TAMAÑOPANTALLA NO EXISTE VERIFICAMOS PARA QUE NO IMPRIMA
            if(producto.getTamanoPantalla() > 0){
                productoJSON.put("tamanoPantalla",producto.getTamanoPantalla());
            }

            jsonArray.put(productoJSON);
        }
        catalogo.put("catalogo",jsonArray);

        try {
            FileWriter fw = new FileWriter("catalogo.json");
            fw.write(catalogo.toString(4));

            fw.close();
            System.out.println("Archivo jsson creado corectamente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}