package fichero14xmlEscritura;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            String archivoXML = "peliculas";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element raiz = doc.createElement("peliculas");
            doc.appendChild(raiz);


            String[] peliculas = {"a1","a2","a3","a4"};
            String[] titulos = {"El corredor del laberinto","Divergente","Los Juegos Del Hambre","Prueba de Fuego"};
            String[] anioLanzamientos ={"2014","2014","2012","2015"};
            String[] directores = {"Wes Ball","Neil Burger","Gary Ross","Wes Ball"};
            String[] generos = {"Ciencia ficción","Ciencia ficción","Acción","Acción"};

            for (int i=0; i<peliculas.length; i++){

                Element pelicula = doc.createElement("pelicula");
                pelicula.setAttribute("id",peliculas[i]);
                raiz.appendChild(pelicula);

                Element titulo = doc.createElement("titulo");
                titulo.setTextContent(titulos[i]);
                pelicula.appendChild(titulo);

                Element anioLanzamiento = doc.createElement("anioLanzamiento");
                anioLanzamiento.setTextContent(anioLanzamientos[i]);
                pelicula.appendChild(anioLanzamiento);

                Element director = doc.createElement("director");
                director.setTextContent(directores[i]);
                pelicula.appendChild(director);

                Element genero = doc.createElement("genero");
                genero.setTextContent(generos[i]);
                pelicula.appendChild(genero);
            }

            TransformerFactory factory1 = TransformerFactory.newInstance();
            Transformer transformer = factory1.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,("yes"));

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(archivoXML+".xml"));

            transformer.transform(source,result);

            System.out.println("Archivo creado correctamente");

            System.out.println("---------------");

            Document docXML = builder.parse(new File(archivoXML+".xml"));
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression ex = xPath.compile("//pelicula");

            NodeList listaPeliculas = (NodeList)ex.evaluate(docXML, XPathConstants.NODESET);

            for(int i=0; i<listaPeliculas.getLength(); i++){
                Node nodoLista = listaPeliculas.item(i);

                if(nodoLista.getNodeType() == Node.ELEMENT_NODE){
                    Element elementoLista = (Element)nodoLista;

                    String id = elementoLista.getAttribute("id");
                    System.out.println("id "+id);

                    String titulo = elementoLista.getElementsByTagName("titulo").item(0).getTextContent();
                    System.out.println("Título "+titulo);

                    String anioLanzamiento = elementoLista.getElementsByTagName("anioLanzamiento").item(0).getTextContent();
                    System.out.println("Año lanzamiento "+anioLanzamiento);

                    String director = elementoLista.getElementsByTagName("director").item(0).getTextContent();
                    System.out.println("Director "+director);

                    String genero = elementoLista.getElementsByTagName("genero").item(0).getTextContent();
                    System.out.println("Género "+genero);


                    System.out.println("-------------------");

                }

            }


        } catch (ParserConfigurationException | TransformerException | IOException | SAXException |
                 XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }
}
