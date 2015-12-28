package com.ca.buro.layout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="campo")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"name", "length"}) 
public class Field {

	private String name;
	private int length;

	@XmlElement(name = "nombre")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "longitud")
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
