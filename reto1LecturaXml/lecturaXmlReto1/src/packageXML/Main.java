package packageXML;

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
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {


        String noticias = "data/noticias.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(noticias));

        doc.getDocumentElement().normalize();
        System.out.println(" ");

        System.out.println("Elemento raiz: " +doc.getDocumentElement().getNodeName());

        Element lista = doc.getDocumentElement();
        Element channel = (Element)lista.getElementsByTagName("channel").item(0);


        String title = channel.getElementsByTagName("title").item(0).getTextContent();
        System.out.println("title: " + title);

        String copyright = channel.getElementsByTagName("copyright").item(0).getTextContent();
        System.out.println("copyright: " + copyright);

        System.out.println(" ");


        NodeList listaAnidada = channel.getElementsByTagName("item");

        for (int i = 0; i < listaAnidada.getLength(); i++) {
            Node nodoLista = listaAnidada.item(i);


            if (nodoLista.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoLista = (Element) nodoLista;

                String titleItem = elementoLista.getElementsByTagName("title").item(0).getTextContent();
                System.out.println("title: " + titleItem);

                String dc = elementoLista.getElementsByTagName("dc:creator").item(0).getTextContent();
                System.out.println("dc:creator: "+dc);

                String categoria = elementoLista.getElementsByTagName("categoria").item(0).getTextContent();
                System.out.println("categoria: " + categoria);

                String media = elementoLista.getElementsByTagName("media:title").item(0).getTextContent();
                String atributo = elementoLista.getElementsByTagName("media:title").item(0).
                        getAttributes().getNamedItem("type").getTextContent();
                System.out.println("media:title type= '"+atributo+"' "+media);

                String pubDate = elementoLista.getElementsByTagName("pubDate").item(0).getTextContent();
                System.out.println("pubDate: " + pubDate);
            }
            System.out.println(" ");
        }

        System.out.println(" ");

    }
}

