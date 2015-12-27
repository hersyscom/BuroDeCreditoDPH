package com.ca.buro.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="campo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseField extends MessageField {

	@XmlElement(name="nombre")
	public String getName() {
		return super.getName();
	}

	@XmlElement(name="valor")
	public String getValue() {
		return super.getValue();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public void setValue(String value) {
		super.setValue(value);
	}
}
