package SensorMLTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBTest {

	public static void main(String[] args) throws JAXBException, IOException {
		 Sensor sensor = new Sensor();
		 TagMember member = new TagMember();
		 TagSystem system = new TagSystem();
		 
		 //First Part
		 TagKeyWords keyWords = new TagKeyWords();
		 List<String> keywords = new ArrayList<String>();
		 keywords.add("sensor bus");
		 keyWords.setKeywords(keywords);
		 
		 //Second Part
		 TagIdentification identification = new TagIdentification();
		 TagIdentifier Identifier = new TagIdentifier();
		 List<TagIdentifier> identifiers = new ArrayList<TagIdentifier>();
		 TagTerm term = new TagTerm();
		 term.setDefinition("urn:ogc:def:identifier:OGC:uniqueID");
		 term.setValue("Sensor-10");
		 Identifier.setTerm(term);
		 identifiers.add(Identifier);
		 identification.setIdentifiers(identifiers);

		 //Third Part
		 TagValidTime validTime = new TagValidTime();
		 TagTimePeriod timePeriod = new TagTimePeriod();
		 timePeriod.setBeginPosition("2011-01-01");
		 timePeriod.setEndPosition("2012-02-02");
		 validTime.setTimePeriod(timePeriod);
		 
		 //Fourth Part
		 TagCapabilities capabilities = new TagCapabilities();
		 List<TagField> fields =new ArrayList<TagField>();
		 TagField field = new TagField();
		 TagTime time =new TagTime();
		 TagUom uom = new TagUom();
		 uom.setCode("ms");
		 time.setUom(uom);
		 time.setValue("1000");
		 time.setDefinition("urn:ogc:def:property:OGC:Time");
		 field.setName("samplingTime");
		 field.setTime(time);
		 fields.add(field);
		 capabilities.setFields(fields);
		 
		 //Fifth Part
		 TagPosition position = new TagPosition();
		 TagswePosition sweposition = new TagswePosition();
		 TagLocation location = new TagLocation();
		 List<TagCoordinate> coordinates = new ArrayList<TagCoordinate>();
		 TagCoordinate coordinate = new TagCoordinate();
		 TagCoordinate coordinate2 = new TagCoordinate();
		 TagCoordinate coordinate3 = new TagCoordinate();
		 TagQuantity quantity = new TagQuantity();
		 TagQuantity quantity2 = new TagQuantity();
		 TagQuantity quantity3 = new TagQuantity();
		 TagUom uom2 = new TagUom();
		 uom2.setCode("degree");
		 quantity.setAxisID("X");
		 quantity.setUom(uom2);
		 quantity.setValue("121.46789254497689");
		 coordinate.setName("easting");
		 coordinate.setQuantity(quantity);
		 quantity2.setAxisID("Y");
		 quantity2.setUom(uom2);
		 quantity2.setValue("31.231222671854475");
		 coordinate2.setName("northing");
		 coordinate2.setQuantity(quantity2);
		 quantity3.setAxisID("Z");
		 quantity3.setUom(uom);
		 quantity3.setValue("86.02");
		 coordinate3.setName("altitude");
		 coordinate3.setQuantity(quantity3);
		 coordinates.add(coordinate);
		 coordinates.add(coordinate2);
		 coordinates.add(coordinate3);
		 location.setCoordinates(coordinates);
//		 location.setGmlid("STATION_LOCATION");
		 sweposition.setLocation(location);
		 sweposition.setReferenceFrame("urn:ogc:def:crs:EPSG:4326");
		 position.setPosition(sweposition);
		 position.setName("sensorPosition");
		 
		 
		 
		 
		 system.setDescripition("A sensor in the Sensor Bus");
		 system.setKeywords(keyWords);
		 system.setIdentification(identification);
		 system.setValidTime(validTime);
		 system.setCapabilites(capabilities);
		 system.setPosition(position);
		 
		 
		 member.setSystem(system);
		 sensor.setMember(member);

		 
		 
	     JAXBContext context = JAXBContext.newInstance(sensor.getClass());
	     Marshaller m = context.createMarshaller();
	     m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     StringWriter sw = new StringWriter();
	     m.marshal(sensor,sw);
	     OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("src/SensorMLTemplate/Test.xml"));
	     out.write(sw.toString());
	     out.flush();
	     out.close();
	     System.out.println(sw.toString());
	}

}
