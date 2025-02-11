package fichero13xmlEscritura;

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

            String archivoXML = "agenciaViajes";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element raiz = doc.createElement("agenciaViajes");
            doc.appendChild(raiz);

            Element reservas = doc.createElement("reservas");
            raiz.appendChild(reservas);

            String[] ids = {"a1","a2","a3","a4","a5","a6"};
            String[] nombreClientes = {"Juan Carlos","Sofia Nahir","Karina Paola","Andrea Jessica","Carolina Butron","Mireya Hernandez"};
            String[] destinos = {"Francia","Argentina","Madrid","España","Barcelona","Mallorca"};
            String[] fechaSalidas = {"2024-06-10","2024-07-10","2024-07-10","2024-04-10","2024-06-10","2024-05-10"};
            String[] fechaRegresos = {"2024-06-20","2024-07-20","2024-07-20","2024-04-20","2024-06-20","2024-05-20"};

            for(int i=0; i<ids.length; i++){

                Element reserva = doc.createElement("reserva");
                reserva.setAttribute("id",ids[i]);
                reservas.appendChild(reserva);

                Element nombreCliente = doc.createElement("nombreCliente");
                nombreCliente.setTextContent(nombreClientes[i]);
                reserva.appendChild(nombreCliente);

                Element destino = doc.createElement("destino");
                destino.setTextContent(destinos[i]);
                reserva.appendChild(destino);

                Element fechaSalida = doc.createElement("fechaSalida");
                fechaSalida.setTextContent(fechaSalidas[i]);
                reserva.appendChild(fechaSalida);

                Element fechaRegreso = doc.createElement("fechaRegreso");
                fechaRegreso.setTextContent(fechaRegresos[i]);
                reserva.appendChild(fechaRegreso);

            }

            TransformerFactory factory1 = TransformerFactory.newInstance();
            Transformer transformer = factory1.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(archivoXML+".xml"));

            transformer.transform(source,result);

            System.out.println("Archivo creado correctamente");


            Document docXML = builder.parse(new File(archivoXML+".xml"));
            XPath xpath = XPathFactory.newInstance().newXPath();

            //para sacar un SOLO CLIENTE un dato especifico
            XPathExpression expreNombreCliente = xpath.compile("//reserva[@id='a1']/nombreCliente");  // ojo para el bucle for depende mucho lo que pongamos en esta linea
            String nombreCliente = (String)expreNombreCliente.evaluate(docXML,XPathConstants.STRING);

            XPathExpression expreDestino = xpath.compile("//reserva[@id='a1']/destino");
            String destino = (String)expreDestino.evaluate(docXML,XPathConstants.STRING);

            XPathExpression expreFechaInicio = xpath.compile("//reserva[@id='a1']/fechaSalida");
            String fechaInicio = (String)expreFechaInicio.evaluate(docXML,XPathConstants.STRING);

            XPathExpression expreFechaFin = xpath.compile("//reserva[@id='a1']/fechaRegreso");
            String fechaFin = (String)expreFechaFin.evaluate(docXML,XPathConstants.STRING);


            System.out.println("Nombre cliente: "+nombreCliente);
            System.out.println("Destino: "+destino);
            System.out.println("Fecha inicio: "+fechaInicio);
            System.out.println("Fecha fin: "+fechaFin);

            System.out.println("----------------------------");

            XPathExpression exp = xpath.compile("//reserva");
            NodeList listaReservas = (NodeList)exp.evaluate(docXML,XPathConstants.NODESET);

            for(int i=0; i<listaReservas.getLength(); i++){
                Node nodoReserva = listaReservas.item(i);

                if(nodoReserva.getNodeType() == Node.ELEMENT_NODE){
                    Element elementoReserva = (Element)nodoReserva;

                    String id = elementoReserva.getAttribute("id");
                    String nombreClient = elementoReserva.getElementsByTagName("nombreCliente").item(0).getTextContent();
                    String destin = elementoReserva.getElementsByTagName("destino").item(0).getTextContent();
                    String fechaInici = elementoReserva.getElementsByTagName("fechaSalida").item(0).getTextContent();
                    String fechaFi = elementoReserva.getElementsByTagName("fechaRegreso").item(0).getTextContent();

                    System.out.println("id: "+id);
                    System.out.println("Nombre cliente: "+nombreClient);
                    System.out.println("Destino: "+destin);
                    System.out.println("Fecha inicio: "+fechaInici);
                    System.out.println("Fecha fin: "+fechaFi);

                    System.out.println("------------------------");
                }
            }
        } catch (ParserConfigurationException | TransformerException | IOException | SAXException |
                 XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }
}
