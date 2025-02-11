package fichero17xmlLectura;

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
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String archivoxml = "uax";
        Main procesos = new Main();
        try {


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element uax = doc.createElement("uax");
            uax.setAttribute("lugar","Brunete");
            uax.setAttribute("pais","España");
            doc.appendChild(uax);

            Element modulo = doc.createElement("modulo");
            modulo.setAttribute("ciclo","DAM");
            uax.appendChild(modulo);

            Element alumno = doc.createElement("alumno");
            alumno.appendChild(doc.createTextNode("Luis"));
            modulo.appendChild(alumno);

            alumno = doc.createElement("alumno");
            alumno.appendChild(doc.createTextNode("Jose"));
            modulo.appendChild(alumno);

            modulo = doc.createElement("modulo");
            modulo.setAttribute("ciclo","DAW");
            uax.appendChild(modulo);

            alumno = doc.createElement("alumno");
            alumno.appendChild(doc.createTextNode("Maria"));
            modulo.appendChild(alumno);

            alumno = doc.createElement("alumno");
            alumno.appendChild(doc.createTextNode("Pedro"));
            modulo.appendChild(alumno);


            TransformerFactory factory1 = TransformerFactory.newInstance();
            Transformer transformer = factory1.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivoxml+".xml");

            transformer.transform(source,result);
            System.out.println("Documento creado");



        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }

        procesos.lecturaxml(archivoxml);
    }
    public void lecturaxml(String fichero){

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(fichero+".xml"));

            doc.getDocumentElement().normalize();
            System.out.println("Documento normalizado");

            Element uax =doc.getDocumentElement();
            String lugar = uax.getAttribute("lugar");
            String pais = uax.getAttribute("pais");

            System.out.println("lugar "+lugar);
            System.out.println("pais "+pais);
            System.out.println("-----------------------");

            NodeList listaModulo = doc.getElementsByTagName("modulo");
            for(int i=0; i< listaModulo.getLength(); i++){
                Node nodoModulo = listaModulo.item(i);

                if(nodoModulo.getNodeType() == Node.ELEMENT_NODE){
                    Element modulo = (Element)nodoModulo;
                    String ciclo = modulo.getAttribute("ciclo");
                    System.out.println("ciclo "+ciclo);

                    NodeList listaAlumno = modulo.getElementsByTagName("alumno");
                    for (int j=0; j<listaAlumno.getLength(); j++){
                        Node nodoAlumnos = listaAlumno.item(j);

                        if(nodoAlumnos.getNodeType() == Node.ELEMENT_NODE){
                            Element alumnos = (Element)nodoAlumnos;

                            System.out.println("Alumno "+alumnos.getTextContent());
                        }
                        System.out.println("------------------");
                    }

                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
