package fichero2;

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
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("data/agencia.xml"));
            System.out.println(" ");
            doc.getDocumentElement().normalize();
            System.out.println("El nodo principal es: "+doc.getDocumentElement().getNodeName());

            NodeList lista = doc.getElementsByTagName("propiedad");

            for(int i=0; i<lista.getLength(); i++){
                Node nodo = lista.item(i);

                if( nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element)nodo;


                    String id = elemento.getAttribute("id");
                    System.out.println("Id: "+id);

                    String tipoPropiedad = elemento.getElementsByTagName("tipoPropiedad").getLength() > 0?
                            elemento.getElementsByTagName("tipoPropiedad").item(0).getTextContent():"N/A";
                    String ciudad = elemento.getElementsByTagName("ciudad").getLength() > 0?
                            elemento.getElementsByTagName("ciudad").item(0).getTextContent():"N/A";
                    String precioSrt = elemento.getElementsByTagName("precio").getLength() >0?
                            elemento.getElementsByTagName("precio").item(0).getTextContent():"N/A";

                    if(id.equals("a1")){
                        System.out.println("el Id primero es: "+id);
                    }
                    if(!tipoPropiedad.equals("N/A")){
                        System.out.println("Tipo de propiedad: "+tipoPropiedad);
                    }
                    if(!ciudad.equals("N/A")){
                        System.out.println("Ciudad: "+ciudad);
                    }
                    if(!precioSrt.equals("N/A")){
                        int precio = Integer.parseInt(precioSrt);
                        System.out.println("Precio: "+precio);

                        if(precio == 350000){
                            System.out.println("El tipo de propiedad que vale 35000 es: "+tipoPropiedad);
                        }

                    }else{
                        System.out.println("Precio: N/A");
                    }


                    System.out.println("------------------------------");
                }
            }



        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
