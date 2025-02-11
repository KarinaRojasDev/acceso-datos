package fichero12xmlLectura;

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

            String elementoRaiz = doc.getDocumentElement().getNodeName();

            System.out.println("Elemento raiz: "+elementoRaiz);

            NodeList lista = doc.getElementsByTagName("recetas");

            for(int i=0; i < lista.getLength(); i++){
                Node nodo = lista.item(i);

                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element)nodo;

                    String titulo = elemento.getElementsByTagName("titulo").getLength() >0?
                            elemento.getElementsByTagName("titulo").item(0).getTextContent() :"N/A";

                    String dificultad = elemento.getElementsByTagName("dificultad").getLength() >0?
                            elemento.getElementsByTagName("dificultad").item(0).getTextContent() :"N/A";

                    String tiempo = elemento.getElementsByTagName("tiempo").getLength() >0?
                            elemento.getElementsByTagName("tiempo").item(0).getTextContent() :"N/A";

                    String porciones = elemento.getElementsByTagName("porciones").getLength() >0?
                            elemento.getElementsByTagName("porciones").item(0).getTextContent() :"N/A";

                    String ingredientes = elemento.getElementsByTagName("ingredientes").getLength() >0?
                            elemento.getElementsByTagName("ingredientes").item(0).getTextContent() :"N/A";

                   NodeList ingredienteLista = elemento.getElementsByTagName("ingrediente");

                   for(int j=0; j<ingredienteLista.getLength(); j++){
                       Node listaIngrediente = ingredienteLista.item(j);

                       if(listaIngrediente.getNodeType() == Node.ELEMENT_NODE){
                           Element listaIng = (Element)listaIngrediente;

                           String nombre = listaIng.getElementsByTagName("nombre").getLength() >0?
                                   listaIng.getElementsByTagName("nombre").item(0).getTextContent() :"N/A";

                           String cantidad = listaIng.getElementsByTagName("cantidad").getLength() >0?
                                   listaIng.getElementsByTagName("cantidad").item(0).getTextContent() :"N/A";

                           String unidad = listaIng.getElementsByTagName("unidad").getLength() >0?
                                   listaIng.getElementsByTagName("unidad").item(0).getTextContent() :"N/A";

                           if(nombre.equalsIgnoreCase("manzanas")){
                               System.out.println("Se necesita "+cantidad+" "+nombre+" para realizar una tarde de manzana");
                           }

                           if(!nombre.equals("N/A") & !cantidad.equals("N/A") & !unidad.equals("N/A")){
                               System.out.println("Los ingredientes para una "+titulo+ " son \nIngredientes: "+nombre+" \nCantidad "+cantidad+" "+unidad);
                           }

                       }
                   }

                    if(!titulo.equals("N/A") & dificultad.equalsIgnoreCase("Media")){
                        System.out.println("La "+titulo+" tiene una dificultadad "+dificultad);
                    }
                    if(!tiempo.equals("N/A")){
                        System.out.println("Tiempo "+tiempo);
                    }if(!porciones.equals("N/A")){
                        System.out.println("Porciones "+porciones);
                    }if(!ingredientes.equals("N/A")){
                        System.out.println("Ingredientes: "+ingredientes);
                    }
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
