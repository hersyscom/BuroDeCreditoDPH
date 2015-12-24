package com.ca.buro;

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
public class Transaccion {
	private String nombre;
	private Segmento[] segmentos;

	public Segmento[] getSegmentos() {
		return segmentos;
	}

	public void setSegmentos(Segmento[] segmentos) {
		this.segmentos = segmentos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlAnyElement
	public List<JAXBElement<Segmento>> getSegmento() {
	    List<JAXBElement<Segmento>> elements = new ArrayList<JAXBElement<Segmento>>();
	    for (Segmento segmento: segmentos) {
	        elements.add(new JAXBElement<Segmento>(new QName(segmento.getIdentificador()), Segmento.class, segmento));
	    }
	    return elements;
	}
}
