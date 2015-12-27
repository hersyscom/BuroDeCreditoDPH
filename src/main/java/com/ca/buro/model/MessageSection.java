package com.ca.buro.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MessageSection {

	private String name;
	private List<MessageField> fields = new ArrayList<MessageField>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MessageField> getFields() {
		return fields;
	}

	public void setFields(List<MessageField> fields) {
		this.fields = fields;
	}

	public void addField(MessageField field) {
		fields.add(field);
	}

}
