package com.ca.buro.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"name", "fields"}) 
public class ResponseSection extends MessageSection {

	@XmlElement(name="nombre")
	public String getName() {
		return super.getName();
	}

    @Override
	public void setName(String name) {
		super.setName(name);
	}

	@XmlElementWrapper(name = "campos")
    @XmlElement(name = "campo",  type=ResponseField.class)
	public List<MessageField> getFields() {
		return super.getFields();
	}

	@Override
	public void setFields(List<MessageField> fields) {
		super.setFields(fields);
	}
}
