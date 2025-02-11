package fichero4;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {



        try {
            String fichero = "torneo";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element raiz = doc.createElement("torneo");
            doc.appendChild(raiz);

            Element equipo = doc.createElement("equipo");
            raiz.appendChild(equipo);

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent("Atletic");
            equipo.appendChild(nombre);

            Element jugadores = doc.createElement("jugadores");
            jugadores.setTextContent("4");


            Element partido = doc.createElement("partido");
            raiz.appendChild(partido);

            Element equipo1 = doc.createElement("equipo1");
            equipo1.setTextContent("Atletic");
            partido.appendChild(equipo1);

            Element equipo2 = doc.createElement("equipo2");
            equipo2.setTextContent("Real Madrid");
            partido.appendChild(equipo2);

            Element resultado = doc.createElement("resultado");
            resultado.setTextContent("1 - 0");
            partido.appendChild(resultado);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new FileOutputStream(fichero+".xml"));

            transformer.transform(source,result);

            System.out.println(" ");
            System.out.println("Datos actualizados en el fichero XML");

        } catch (ParserConfigurationException | FileNotFoundException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
