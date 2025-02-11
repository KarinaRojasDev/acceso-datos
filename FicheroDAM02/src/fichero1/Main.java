package fichero1;

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
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("data/catalogo.xml"));

            doc.getDocumentElement().normalize();
            String nodoPrincipal = doc.getDocumentElement().getNodeName();
            System.out.println(nodoPrincipal);

            NodeList lista =  doc.getElementsByTagName("producto");
            for(int i=0; i<lista.getLength(); i++){
                 Node nodo = lista.item(i);

                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element)nodo;

                    String id = element.getAttribute("id");
                    System.out.println("Id :"+ id);
                    String marca = element.getElementsByTagName("marca").getLength() > 0?
                            element.getElementsByTagName("marca").item(0).getTextContent() : "N/A";
                    String sistemaOperativo = element.getElementsByTagName("sistemaOperativo").getLength() > 0?
                            element.getElementsByTagName("sistemaOperativo").item(0).getTextContent() : "N/A";

                    if(!marca.equals("N/A")){
                        System.out.println("Marca: "+marca);
                    }
                    if(!sistemaOperativo.equals("N/A") & sistemaOperativo.equalsIgnoreCase("android")){
                        System.out.println("Sistema operativo Andriod ");
                    }
                    System.out.println("----------------");

                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
