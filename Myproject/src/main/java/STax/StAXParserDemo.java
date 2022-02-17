package STax;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class StAXParserDemo {
    public static void main(String[] args) {
        boolean bFirstName = false;
        boolean bLastName = false;
        boolean bNickName = false;
        boolean bMarks = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader("students.xml"));

            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch(event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("student")) {
                            System.out.println("Start Element : student");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()){
                                Attribute currentAttribute = attributes.next();
                                System.out.println(currentAttribute.getName() + "  : " + currentAttribute.getValue());
                            }
                        } else if (qName.equalsIgnoreCase("firstname")) {
                            bFirstName = true;
                        } else if (qName.equalsIgnoreCase("lastname")) {
                            bLastName = true;
                        } else if (qName.equalsIgnoreCase("nickname")) {
                            bNickName = true;
                        }
                        else if (qName.equalsIgnoreCase("marks")) {
                            bMarks = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(bFirstName) {
                            System.out.println("First Name: " + characters.getData());
                            bFirstName = false;
                        }
                        if(bLastName) {
                            System.out.println("Last Name: " + characters.getData());
                            bLastName = false;
                        }
                        if(bNickName) {
                            System.out.println("Nick Name: " + characters.getData());
                            bNickName = false;
                        }
                        if(bMarks) {
                            System.out.println("Marks: " + characters.getData());
                            bMarks = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if(endElement.getName().getLocalPart().equalsIgnoreCase("student")) {
                            System.out.println("End Element : student");
                            System.out.println();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
