package com.ca.buro.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="respuesta")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseMessage extends Message {

	@XmlElement(name="tipo")
	public String getName() {
		return super.getName();
	}
	
    @Override
	public void setName(String name) {
		super.setName(name);
	}

	@XmlElementWrapper(name = "secciones")
    @XmlElement(name = "seccion", type=ResponseSection.class)
	public List<MessageSection> getSections() {
		return super.getSections();
	}

	@Override
	public void setSections(List<MessageSection> sections) {
		super.setSections(sections);
	}
}
