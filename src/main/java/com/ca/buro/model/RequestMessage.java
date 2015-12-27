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
public class RequestMessage extends Message {
	
	@XmlAnyElement
	public List<JAXBElement<RequestSection>> getSection() {
		List<JAXBElement<RequestSection>> elements = new ArrayList<JAXBElement<RequestSection>>();
		for (MessageSection section : this.sections) {
			elements.add(new JAXBElement<RequestSection>(new QName(section.getName()), RequestSection.class, (RequestSection) section));
		}
		return elements;
	}

}
