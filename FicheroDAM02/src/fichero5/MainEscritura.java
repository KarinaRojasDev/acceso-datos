package fichero5;

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
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainEscritura {
    public static void main(String[] args) {

        try {

            String archivoXML = "propiedades";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element raiz = doc.createElement("propiedades");
            doc.appendChild(raiz);

            Element propiedad1 = doc.createElement("propiedad1");
            propiedad1.setAttribute("id","al");
            propiedad1.setAttribute("lote","1");
            raiz.appendChild(propiedad1);

            Element propiedad2 = doc.createElement("propiedad2");
            propiedad2.setAttribute("id","a2");
            raiz.appendChild(propiedad2);

            Element tipoPropiedad1 = doc.createElement("tipoPropiedad1");
            tipoPropiedad1.setTextContent("Casa");
            propiedad1.appendChild(tipoPropiedad1);

            Element tipoPropiedad2 = doc.createElement("tipoPropiedad2");
            tipoPropiedad2.setTextContent("Departamento");
            propiedad2.appendChild(tipoPropiedad2);

            Element ubicacion1 = doc.createElement("ubicacion");
            propiedad1.appendChild(ubicacion1);

            Element ubicacion2 = doc.createElement("ubicacion");
            propiedad2.appendChild(ubicacion2);

            Element ciudad1 = doc.createElement("ciudad1");
            ciudad1.setTextContent("Ney York");
            ubicacion1.appendChild(ciudad1);

            Element ciudad2 = doc.createElement("ciudad2");
            ciudad2.setTextContent("Los Angeles");
            ubicacion2.appendChild(ciudad2);

            Element codigoPostal1 = doc.createElement("codigoPostal1");
            codigoPostal1.setTextContent("10001");
            ubicacion1.appendChild(codigoPostal1);

            Element codigoPostal2 = doc.createElement("codigoPostal2");
            codigoPostal2.setTextContent("90001");
            ubicacion2.appendChild(codigoPostal2);


            Element precio1 = doc.createElement("precio1");
            precio1.setTextContent("600000");
            ubicacion1.appendChild(precio1);

            Element precio2 = doc.createElement("precio2");
            precio2.setTextContent("350000");
            ubicacion2.appendChild(precio2);

            Element metrosCuadrados1 = doc.createElement("metrosCuadrados");
            metrosCuadrados1.setTextContent("250");;
            ubicacion1.appendChild(metrosCuadrados1);

            Element metrosCuadrados2 = doc.createElement("metrosCuadrados2");
            metrosCuadrados2.setTextContent("150");
            ubicacion2.appendChild(metrosCuadrados2);

            TransformerFactory transFactory = TransformerFactory.newInstance();

            Transformer transformer = transFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(archivoXML +".xml"));

            transformer.transform(source,result);

            Document docXML = builder.parse(new File(archivoXML + ".xml"));
            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expre = xpath.compile("propiedades/propiedad1/tipoPropiedad1 | propiedades/propiedad2/tipoPropiedad2");

            String tipoPropiedad = (String)expre.evaluate(docXML, XPathConstants.STRING);

            System.out.println("Tipo de propiedad: "+tipoPropiedad);

            NodeList lista = (NodeList) expre.evaluate(docXML,XPathConstants.NODESET);

            for(int i=0; i<lista.getLength(); i++){
                System.out.println("Tipos de propiedades son: "+lista.item(i).getTextContent());
            }


        } catch (ParserConfigurationException | TransformerException | IOException | SAXException |
                 XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }
}
