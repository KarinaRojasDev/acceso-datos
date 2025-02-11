package fichero15jsonEscritura;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Peliculas> pelicula = new ArrayList<>();
        pelicula.add(new Peliculas("El corredor del laberinto",2014,"Wes Ball","Ciencia ficción"));
        pelicula.add(new Peliculas("Divergente",2014,"Neil Burger","Ciencia ficción"));

        JSONObject lista = new JSONObject();
        JSONArray array = new JSONArray();

        for(Peliculas listaPeliculas: pelicula){
            JSONObject listaPeliculasJSON = new JSONObject();

            listaPeliculasJSON.put("titulo",listaPeliculas.getTitulo());
            listaPeliculasJSON.put("anioLanzamiento",listaPeliculas.getAnioLanzamiento());
            listaPeliculasJSON.put("director",listaPeliculas.getDirector());
            listaPeliculasJSON.put("genero",listaPeliculas.getGenero());

            array.put(listaPeliculasJSON);
        }
        lista.put("peliculas",array);

        try {

            FileWriter fw = new FileWriter("peliculas.json");
            fw.write(lista.toString(4));
            fw.close();

            System.out.println("Archivo JSON creado correctamente ");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
