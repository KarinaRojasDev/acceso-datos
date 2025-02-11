package fichero3;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {


        try {
            String fichero = "casa_rurales";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element raiz = doc.createElement("casa_rurales");
            doc.appendChild(raiz);

            Element casa = doc.createElement("casa");
            casa.setAttribute("id","a1");
            raiz.appendChild(casa);

            Element direccion = doc.createElement("direccion");
            direccion.appendChild(doc.createTextNode("Avenida universidad"));
            casa.appendChild(direccion);

            Element descripcion = doc.createElement("descripcion");
            descripcion.appendChild(doc.createTextNode("Casa perfecta de la UAX"));
            casa.appendChild(descripcion);

            Element estado = doc.createElement("estado");
            estado.appendChild(doc.createTextNode("Disponible"));
            casa.appendChild(estado);

            Element tamanio = doc.createElement("tamanio");
            tamanio.appendChild(doc.createTextNode("100m2"));
            casa.appendChild(tamanio);

            casa = doc.createElement("casa");
            casa.setAttribute("id","a2");
            raiz.appendChild(casa);

            direccion = doc.createElement("direccion");
            direccion.appendChild(doc.createTextNode("Avenida universidad2"));
            casa.appendChild(direccion);

            descripcion = doc.createElement("descripcion");
            descripcion.appendChild(doc.createTextNode("Casa perfecta de la UAX2"));
            casa.appendChild(descripcion);

            estado = doc.createElement("estado");
            estado.appendChild(doc.createTextNode("Disponible2"));
            casa.appendChild(estado);

            tamanio = doc.createElement("tamanio");
            tamanio.appendChild(doc.createTextNode("100m2.2"));
            casa.appendChild(tamanio);



            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new FileOutputStream(fichero +".xml"));

            transformer.transform(source,result);
            System.out.println(" ");
            System.out.println("Datos actualizados en el archivo XML");



        } catch (ParserConfigurationException | FileNotFoundException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
