package com.ca.buro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.buro.layout.Field;
import com.ca.buro.layout.Section;
import com.ca.buro.layout.Template;

public class INTLBuilder {
	private static final String INTL_SIN_AUTENTICACION_REQUEST = "INTSinAutenticacion_request";
	private static final String INTL_SIN_AUTENTICACION_REQUEST_FILE = "/com/ca/buro/" + INTL_SIN_AUTENTICACION_REQUEST + ".xml";
	private static final String INTL_SIN_AUTENTICACION_RESPONSE = "INTSinAutenticacion_response";
	private static final String INTL_SIN_AUTENTICACION_RESPONSE_FILE = "/com/ca/buro/" + INTL_SIN_AUTENTICACION_RESPONSE + ".xml";
	
	private static final String TEMPLATE_TO_XML_ERROR = "Error al convertir el XML en plantilla.";
	private static final String XML_TO_TEMPLATE_ERROR = "Error al convertir la plantilla en XML.";
	
	private static final INTLBuilder INSTANCE = new INTLBuilder();
	private final Logger logger = LoggerFactory.getLogger(INTLBuilder.class);
	private Map<String, Template> templates = new HashMap<String, Template>();
	private JAXBContext jaxbContext;

	private INTLBuilder() {
		try {
			jaxbContext = JAXBContext.newInstance(Template.class, Section.class, Field.class);
		} catch (JAXBException e) {
		}
		templates.put(INTL_SIN_AUTENTICACION_REQUEST, xmlToTemplate(INTL_SIN_AUTENTICACION_REQUEST_FILE));
		if(logger.isDebugEnabled()) {
			logger.debug(templateToXML(templates.get(INTL_SIN_AUTENTICACION_REQUEST)));
		}
		templates.put(INTL_SIN_AUTENTICACION_RESPONSE, xmlToTemplate(INTL_SIN_AUTENTICACION_RESPONSE_FILE));
		if(logger.isDebugEnabled()) {
			logger.debug(templateToXML(templates.get(INTL_SIN_AUTENTICACION_RESPONSE)));
		}
	}
	
	private String templateToXML(Template template) {
		String xmlString = null;
		try {
			StringWriter sw = new StringWriter();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.marshal(template, sw);
			xmlString = sw.toString();
		} catch (JAXBException e) {
			logger.error(TEMPLATE_TO_XML_ERROR, e);
		}
		return xmlString;
	}
	
	private Template xmlToTemplate(String fileName) {
		Template template  = null;
		try {
			Reader reader = new BufferedReader(new InputStreamReader(INTLBuilder.class.getResourceAsStream(fileName)));
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			template = (Template) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			logger.error(XML_TO_TEMPLATE_ERROR, e);
		}
		return template;
	}

	public Template buildRequestINTL11SinAutenticacion() {
		return templates.get(INTL_SIN_AUTENTICACION_REQUEST);
	}
	
	public Template buildResponseINTL11SinAutenticacion() {
		return templates.get(INTL_SIN_AUTENTICACION_RESPONSE);
	}
	
	public static INTLBuilder getInstance() {
		return INSTANCE;
	}
}
