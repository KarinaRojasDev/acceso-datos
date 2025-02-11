package fichero;

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

        String archivoXml = "data/bebidas.xml";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false); // validar archivo xml que tenga dtd
            factory.setIgnoringElementContentWhitespace(true); //elimina los espacios del archivo xml

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(archivoXml));

            doc.getDocumentElement().normalize(); //Invocamos getDocumentElement. normalize para la NORMALIZACIÓN DE ESE DOCUMENTO
            System.out.println("Elemento raiz: "+doc.getDocumentElement().getNodeName());  //sacamos el NOMBRE DEL NODO RAIZ

            NodeList listaBebidas = doc.getElementsByTagName("bebida");

            for(int i=0; i<listaBebidas.getLength(); i++){
                Node nodo = listaBebidas.item(i);

                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element bebida = (Element) nodo;

                    String nombre = bebida.getElementsByTagName("nombre").getLength() >0 ?
                            bebida.getElementsByTagName("nombre").item(0).getTextContent(): "N/A";
                    String codigo = bebida.getElementsByTagName("codigo").getLength() > 0?
                            bebida.getElementsByTagName("codigo").item(0).getTextContent(): "N/A";
                    String precio = bebida.getElementsByTagName("precio").getLength() > 0?
                            bebida.getElementsByTagName("precio").item(0).getTextContent(): "N/A";

                    System.out.println(" "); // para que divida y haga una separación de datos
                    System.out.println("Bebidas "+(i+1)+" :");

                    if(!nombre.equals("N/A")){
                        System.out.println("Nombre "+nombre);
                    }
                    if(!codigo.equals("N/A")){
                        System.out.println("Codigo "+codigo);
                    }
                    if(!precio.equals("N/A")){
                        System.out.println("Precio "+precio);
                    }
                }
            }


        } catch (ParserConfigurationException e) {
            System.err.println("Error de configuración del parser: " + e.getMessage());
        } catch (SAXException e) {
            System.err.println("Error en el archivo XML: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

    }
}
