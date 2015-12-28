package com.ca.buro.layout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="seccion")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"id", "labelIncluded", "fields"}) 
public class Section {
	private String id;
	private boolean labelIncluded;
	private Field[] fields;

    @XmlElement(name = "identificador")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "seIncluyenEtiquetas")
	public boolean isLabelIncluded() {
		return labelIncluded;
	}

	public void setLabelIncluded(boolean labelIncluded) {
		this.labelIncluded = labelIncluded;
	}

	@XmlElementWrapper(name = "campos")
    @XmlElement(name = "campo", type=Field.class)
	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}
}