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
public class Transaction {
	private String name;
	private List<Section> sections = new ArrayList<Section>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public void addSection(Section section) {
		sections.add(section);
	}
	@XmlAnyElement
	public List<JAXBElement<Section>> getSection() {
	    List<JAXBElement<Section>> elements = new ArrayList<JAXBElement<Section>>();
	    for (Section section: sections) {
	        elements.add(new JAXBElement<Section>(new QName(section.getName()), Section.class, section));
	    }
	    return elements;
	}
}
