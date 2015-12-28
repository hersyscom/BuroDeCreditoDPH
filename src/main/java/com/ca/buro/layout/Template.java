package com.ca.buro.layout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="plantilla")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"name", "sections"}) 
public class Template {
	private String name;
	private Section[] sections;

    @XmlElement(name = "nombre")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElementWrapper(name = "secciones")
    @XmlElement(name = "seccion", type=Section.class)
	public Section[] getSections() {
		return sections;
	}

	public void setSections(Section[] sections) {
		this.sections = sections;
	}
}
