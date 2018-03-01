import generated.Horse;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void  main(String []args){
        try {
            String example =
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header /><soapenv:Body><ns2:farm xmlns:ns2=\"http://adamish.com/example/farm\"><horse height=\"123\" name=\"glue factory\"/></ns2:farm></soapenv:Body></soapenv:Envelope>";
           /* SOAPMessage message = MessageFactory.newInstance().createMessage(null,
                    new ByteArrayInputStream(example.getBytes()));
            Unmarshaller unmarshaller = JAXBContext.newInstance(Farm.class).createUnmarshaller();
            Farm farm = (Farm) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
            System.out.print(farm);
*//*
            int start = example.indexOf("Body>") + 5;
            Matcher m = Pattern.compile("</[^>]*Body").matcher(example);
            m.find();
            int end = m.start();
            String output = example.substring(start, end);
            Unmarshaller unmarshaller = JAXBContext.newInstance(Farm.class).createUnmarshaller();
            Farm farm = (Farm) unmarshaller.unmarshal(new ByteArrayInputStream(output.getBytes()));

            JAXBContext context = JAXBContext.newInstance(Farm.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(farm, sw);
            String xmlString = sw.toString();
            System.out.print(xmlString);
*/

            /*Marshalling*/

            Farm farm1 = new Farm();
            farm1.setHorse((new Horse()));
            farm1.getHorse().setName("glue factory");
            farm1.getHorse().setHeight("123");

            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Marshaller marshaller = JAXBContext.newInstance(Farm.class).createMarshaller();
            marshaller.marshal(farm1, document);
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
            soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration("soap", "http://www.w3.org/2001/12/soap-envelope");
            soapMessage.getSOAPPart().getEnvelope().setPrefix("soap");
            soapMessage.getSOAPHeader().setPrefix("soap");
            soapMessage.getSOAPBody().setPrefix("soap");
            soapMessage.getSOAPBody().addDocument(document);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            soapMessage.writeTo(outputStream);
            String output = new String(outputStream.toByteArray());
            System.out.print(output);
        }catch (Exception e){
            System.out.print("Error");
        }

    }
}
