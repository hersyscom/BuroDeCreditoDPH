package com.ca.buro.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Section {
	private String name;
	private List<Field> fields = new ArrayList<Field>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public void addField(Field field) {
		fields.add(field);
	}
	@XmlAnyElement
	public List<JAXBElement<String>> getField() {
	    List<JAXBElement<String>> elements = new ArrayList<JAXBElement<String>>();
	    for(Field field: fields) {
    		elements.add(new JAXBElement<String>(new QName(field.getName()), String.class, "<![CDATA[" + field.getValue() + "]]>"));
	    }
	    return elements;
	}
}
