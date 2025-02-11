package packageEscrituraJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner snc = new Scanner(System.in);

        Titulo titulo = new Titulo("Heroes");

        System.out.println(" ");
        System.out.println("Introduce los datos de los héroes. Si deseas salir del programa escribe '*'");

        String name, link, img;
        int size;
        String hero = "";


        JSONObject listaHeroes = new JSONObject();

        listaHeroes.put("hero", titulo.getNombre());

        JSONArray arrayHeroes = new JSONArray();


        while (!hero.equals("*")) {
            System.out.println("Heroe o '*' si deseas salir");
            hero = snc.nextLine();
            if (!hero.equals("*")) {
                System.out.println("Nombre del héroe");
                name = snc.nextLine();

                System.out.println("Enlace asociado al héroe ");
                link = snc.nextLine();

                System.out.println("URL de la imagen del héroe ");
                img = snc.nextLine();

                System.out.println("Tamaño del héroe (número entero)");
                size = snc.nextInt();
                snc.nextLine();

                Heroe heroes =new Heroe(hero, name, link, img, size);
                titulo.add(heroes);


                JSONObject heroesListJSON = new JSONObject();
                heroesListJSON.put("hero", heroes.getHero());
                heroesListJSON.put("name", heroes.getName());
                heroesListJSON.put("link", heroes.getLink());
                heroesListJSON.put("img", heroes.getImg());
                heroesListJSON.put("size", heroes.getSize());

                arrayHeroes.put(heroesListJSON);

            }
        }

        listaHeroes.put("heroes", arrayHeroes);

        try (FileWriter fw = new FileWriter("heroes.json");) {
            fw.write(listaHeroes.toString(4));
            System.out.println("Lista de heroes creado correctamente");
        }catch (IOException e){
            System.out.println("Error al escribir el archivo "+e.getMessage());
        }
    }
}
