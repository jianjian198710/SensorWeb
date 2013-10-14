package Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import JavaBean.Sensor;
import SensorMLTemplate.TagCapabilities;
import SensorMLTemplate.TagCoordinate;
import SensorMLTemplate.TagField;
import SensorMLTemplate.TagIdentification;
import SensorMLTemplate.TagIdentifier;
import SensorMLTemplate.TagInput;
import SensorMLTemplate.TagInputs;
import SensorMLTemplate.TagKeyWords;
import SensorMLTemplate.TagLocation;
import SensorMLTemplate.TagMember;
import SensorMLTemplate.TagObservableProperty;
import SensorMLTemplate.TagOutput;
import SensorMLTemplate.TagOutputs;
import SensorMLTemplate.TagPosition;
import SensorMLTemplate.TagQuantity;
import SensorMLTemplate.TagSensorML;
import SensorMLTemplate.TagSystem;
import SensorMLTemplate.TagTerm;
import SensorMLTemplate.TagTime;
import SensorMLTemplate.TagTimePeriod;
import SensorMLTemplate.TagUom;
import SensorMLTemplate.TagValidTime;
import SensorMLTemplate.TagVector;
import SensorMLTemplate.TagswePosition;

public class CreateSensorMLServiceImp {

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public CreateSensorMLServiceImp(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void outputToSensroML(HttpServletRequest request, HttpServletResponse response) throws JAXBException, IOException {
		String sensorID = request.getParameter("sensorID");
		String description = request.getParameter("description");
		String keyword = request.getParameter("keyword");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String samplingTime = request.getParameter("samplingTime");
		String easting = request.getParameter("easting");
		String northing = request.getParameter("northing");
		String altitude = request.getParameter("altitude");
		String sobservableProperty = request.getParameter("observableProperty");
		String suom = request.getParameter("uom");

		System.out.println("Start to create XML...");
		TagSensorML sensorML = new TagSensorML();
		TagMember member = new TagMember();
		TagSystem system = new TagSystem();

		// First Part
		TagKeyWords keyWords = new TagKeyWords();
		List<String> keywords = new ArrayList<String>();
		keywords.add(keyword);
		keyWords.setKeywords(keywords);

		// Second Part
		TagIdentification identification = new TagIdentification();
		TagIdentifier Identifier = new TagIdentifier();
		List<TagIdentifier> identifiers = new ArrayList<TagIdentifier>();
		TagTerm term = new TagTerm();
		term.setDefinition("urn:ogc:def:identifier:OGC:uniqueID");
		term.setValue(sensorID);
		Identifier.setTerm(term);
		identifiers.add(Identifier);
		identification.setIdentifiers(identifiers);

		// Third Part
		TagValidTime validTime = new TagValidTime();
		TagTimePeriod timePeriod = new TagTimePeriod();
		timePeriod.setBeginPosition(beginTime);
		timePeriod.setEndPosition(endTime);
		validTime.setTimePeriod(timePeriod);

		// Fourth Part
		TagCapabilities capabilities = new TagCapabilities();
		List<TagField> fields = new ArrayList<TagField>();
		TagField field = new TagField();
		TagTime time = new TagTime();
		TagUom uom = new TagUom();
		uom.setCode(suom);
		time.setUom(uom);
		time.setValue(samplingTime);
		time.setDefinition("urn:ogc:def:property:OGC:Time");
		field.setName("samplingTime");
		field.setTime(time);
		fields.add(field);
		capabilities.setFields(fields);

		// Fifth Part
		TagPosition position = new TagPosition();
		TagswePosition sweposition = new TagswePosition();
		TagLocation location = new TagLocation();
		TagVector vector = new TagVector();
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
		quantity.setValue(easting);
		coordinate.setName("easting");
		coordinate.setQuantity(quantity);
		quantity2.setAxisID("Y");
		quantity2.setUom(uom2);
		quantity2.setValue(northing);
		coordinate2.setName("northing");
		coordinate2.setQuantity(quantity2);
		quantity3.setAxisID("Z");
		quantity3.setUom(uom);
		quantity3.setValue(altitude);
		coordinate3.setName("altitude");
		coordinate3.setQuantity(quantity3);
		coordinates.add(coordinate);
		coordinates.add(coordinate2);
		coordinates.add(coordinate3);
		vector.setCoordinates(coordinates);
		vector.setGmlid("STATION_LOCATION");
		location.setVector(vector);
		sweposition.setLocation(location);
		sweposition.setReferenceFrame("urn:ogc:def:crs:EPSG:4326");
		position.setPosition(sweposition);
		position.setName("sensorPosition");

		// Sixth Part
		TagObservableProperty observableProperty = new TagObservableProperty();
		List<TagInput> inputsList = new ArrayList<TagInput>();
		TagInput input = new TagInput();
		TagInputs inputs = new TagInputs();
		observableProperty.setDefinition(sobservableProperty);
		input.setObservableProperty(observableProperty);
		inputsList.add(input);
		inputs.setInput(inputsList);

		// SeventhPart
		TagUom uom3 = new TagUom();
		TagOutput output = new TagOutput();
		List<TagOutput> outputsList = new ArrayList<TagOutput>();
		TagOutputs outputs = new TagOutputs();
		uom3.setCode("degree");
		output.setUom(uom3);
		output.setName("temperature");
		outputsList.add(output);
		outputs.setOutputs(outputsList);

		system.setDescripition(description);
		system.setKeywords(keyWords);
		system.setIdentification(identification);
		system.setValidTime(validTime);
		system.setCapabilites(capabilities);
		system.setPosition(position);
		system.setInputs(inputs);
		system.setOuputs(outputs);

		member.setSystem(system);
		sensorML.setMember(member);

		JAXBContext context = JAXBContext.newInstance(sensorML.getClass());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		m.marshal(sensorML, sw);
		
		String filename = "C:/WorkSpace/SensorWeb/SensorML/"+sensorID+".xml";
		File file = new File(filename);
		System.out.println(file.getCanonicalPath());
		if(file.exists()){
			System.out.println("文件已经存在!");
		}else{
			System.out.println("Create Finished!!!!!!");
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
			out.write(sw.toString());
			out.flush();
			out.close();
			System.out.println(sw.toString());
		}
	}
}
