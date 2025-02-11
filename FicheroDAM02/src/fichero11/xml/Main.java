package fichero11.xml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {

        String archivo = "archivo_practicas";

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        Document documento = dbBuilder.newDocument();

        // Crear la raíz "Academia"
        Element root = documento.createElement("Academia");
        root.setAttribute("Id", "Academia");
        root.setIdAttribute("Id", true);
        documento.appendChild(root);

        // Añadir primer alumno
        Element hijo1 = documento.createElement("Alumno");
        hijo1.setAttribute("nombre", "Pepe Pérez");
        root.appendChild(hijo1);

        // Añadir asignaturas para el primer alumno
        Element hijo2 = documento.createElement("Asignaturas_matriculadas");
        hijo2.appendChild(documento.createTextNode("Lengua,Física,Matemáticas"));
        hijo1.appendChild(hijo2);

        // Añadir segundo alumno
        Element hijo3 = documento.createElement("Alumno");
        hijo3.setAttribute("nombre", "Maria Garcia");
        root.appendChild(hijo3);

        // Añadir asignaturas para el segundo alumno
        Element hijo4 = documento.createElement("Asignaturas_matriculadas");
        hijo4.appendChild(documento.createTextNode("Griego,Lenguas muertas"));
        hijo3.appendChild(hijo4);

        // Crear el archivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(new FileOutputStream(archivo +".xml"));

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);

        System.out.println("**Archivo creado con éxito**");

//---------------------------------------------------------------------------------------------------------------//

        // Leer el archivo XML que acabas de crear
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document documentoLeido = documentBuilder.parse("archivo_practicas.xml");
        documentoLeido.getDocumentElement().normalize();

        // Leer y mostrar los alumnos y sus asignaturas (IMPORTANTE PARA EXAMEN)
        //yo lo imprimi en un sout  -> sout("Elemento raiz: "+documentoLeido.getDocumentElement().getyNodeName());
        Element raiz = documentoLeido.getDocumentElement();
        NodeList nodeList = raiz.getElementsByTagName("Alumno");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element nodoElement = (Element) nodeList.item(i);
            System.out.println("Alumno " + (i + 1));

            String nombreAlumno = nodoElement.getAttribute("nombre");
            System.out.println("Nombre: " + nombreAlumno);

            // Obtener las asignaturas de cada alumno
            NodeList listaAsignaturas = nodoElement.getElementsByTagName("Asignaturas_matriculadas");
            for (int j = 0; j < listaAsignaturas.getLength(); j++) {
                Element asignaturasElement = (Element) listaAsignaturas.item(j);
                String asignaturas = asignaturasElement.getTextContent();
                System.out.println("Asignaturas matriculadas: " + asignaturas);
            }
        }
    }
}
