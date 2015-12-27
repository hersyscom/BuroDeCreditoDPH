package com.ca.buro.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Message {

	private String name;
	protected List<MessageSection> sections = new ArrayList<MessageSection>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<MessageSection> getSections() {
		return sections;
	}

	public void setSections(List<MessageSection> sections) {
		this.sections = sections;
	}

	public void addSection(MessageSection section) {
		sections.add(section);
	}
}
