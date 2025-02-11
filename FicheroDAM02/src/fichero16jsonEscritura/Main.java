package fichero16jsonEscritura;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Pelicula> listaPeliculas = new ArrayList<>();

        listaPeliculas.add(new Pelicula("El corredor del laberinto",2014,"Wes Ball"));
        listaPeliculas.add(new Pelicula("Divergente",2014,"Neil Burger","Ciencia ficción"));

        JSONObject pelis = new JSONObject();
        JSONArray arrayPelis = new JSONArray();

        for(Pelicula peliculas: listaPeliculas){
            JSONObject peliculasJSON = new JSONObject();

            peliculasJSON.put("titulo",peliculas.getTitulo());
            peliculasJSON.put("anioLanzamiento",peliculas.getAnioLanzamiento());
            peliculasJSON.put("director",peliculas.getDirector());

            if(peliculas.getGenero() !=null && !peliculas.getGenero().equals("N/A")){
                peliculasJSON.put("genero",peliculas.getGenero());
            }
            arrayPelis.put(peliculasJSON);
        }
        pelis.put("peliculass",arrayPelis);

        try {

            FileWriter fw = new FileWriter("peliculass.json");
            fw.write(pelis.toString(4));
            fw.close();
            System.out.println("Archivo creado con exito");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
