package fichero5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {

            String archivoXML = "data/resetas.xml";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(archivoXML));

            doc.getDocumentElement().normalize();

            System.out.println("Elemento raiz: " + doc.getDocumentElement().getNodeName());

            NodeList lista = doc.getElementsByTagName("recetas");
            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;


                    String titulo = elemento.getElementsByTagName("titulo").getLength() > 0 ?
                            elemento.getElementsByTagName("titulo").item(0).getTextContent() : "N/A";
                    String dificultad = elemento.getElementsByTagName("dificultad").getLength() > 0 ?
                            elemento.getElementsByTagName("dificultad").item(0).getTextContent() : "N/A";
                    String ingredientes = elemento.getElementsByTagName("ingredientes").getLength() > 0 ?
                            elemento.getElementsByTagName("ingredientes").item(0).getTextContent() : "N/A";
                    String tiempo = elemento.getElementsByTagName("tiempo").getLength() > 0 ?
                            elemento.getElementsByTagName("tiempo").item(0).getTextContent() : "N/A";
                    String porciones = elemento.getElementsByTagName("porciones").getLength() > 0 ?
                            elemento.getElementsByTagName("porciones").item(0).getTextContent() : "N/A";
                    String ingrediente = elemento.getElementsByTagName("ingrediente").getLength() > 0 ?
                            elemento.getElementsByTagName("ingrediente").item(0).getTextContent() : "N/A";
                    String nombre = elemento.getElementsByTagName("nombre").getLength() > 0 ?
                            elemento.getElementsByTagName("nombre").item(0).getTextContent() : "N/A";
                    String cantidad = elemento.getElementsByTagName("cantidad").getLength() > 0 ?
                            elemento.getElementsByTagName("cantidad").item(0).getTextContent() : "N/A";
                    String unidad = elemento.getElementsByTagName("unidad").getLength() > 0 ?
                            elemento.getElementsByTagName("unidad").item(0).getTextContent() : "N/A";

                    if (!titulo.equals("N/A")) {
                        System.out.println("Título: " + titulo);
                    }
                    if (!dificultad.equals("N/A") && tiempo.equalsIgnoreCase("1 hora")) {
                        System.out.println("Dificultad de hacer una "+titulo+" lleva un tiempo de: " + tiempo);
                    }
                    if (!ingredientes.equals("N/A")) {
                        System.out.println("Ingredientes: " + ingredientes);
                    }if(!cantidad.equals("N/A") && nombre.equalsIgnoreCase("manzanas")){
                        System.out.println("Para realizar una tarta de manzanas necesito "+cantidad+" manzanas ");
                    }if(!tiempo.equals("N/A") ){
                        System.out.println("Tiempo: "+tiempo);
                    }
                }
            }
        } catch(ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
