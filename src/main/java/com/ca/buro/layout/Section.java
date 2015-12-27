package com.ca.buro.layout;

public class Section {
	private String id;
	private boolean labelIncluded;
	private Field[] fields;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isLabelIncluded() {
		return labelIncluded;
	}

	public void setLabelIncluded(boolean labelIncluded) {
		this.labelIncluded = labelIncluded;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}
}