package com.ca.buro.model;

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
public class RequestSection extends MessageSection {

	@XmlAnyElement
	public List<JAXBElement<String>> getField() {
		List<JAXBElement<String>> elements = new ArrayList<JAXBElement<String>>();
		for (MessageField field : this.getFields()) {
			elements.add(new JAXBElement<String>(new QName(field.getName()), String.class, field.getValue()));
		}
		return elements;
	}
}
