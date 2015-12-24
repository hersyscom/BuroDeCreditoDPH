package com.ca.buro;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import com.ca.buro.modelo.Field;
import com.ca.buro.modelo.Section;
import com.ca.buro.modelo.Transaction;
import com.itko.lisa.test.TestExec;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.Response;
import com.itko.lisa.vse.stateful.model.TransientResponse;
import com.itko.lisa.vse.stateful.protocol.DataProtocol;

public class BuroDeCreditoInt11Handler extends DataProtocol {
	private static final int TAMANO_ETIQUETA = 2;
	private static final int TAMANO_LONGITUD = 2;

	private Transaction procesaTransaccion(Transaccion transaccion, String body) {
		Transaction transaction = new Transaction();
		transaction.setName(transaccion.getNombre());
		Apuntador apuntador = new Apuntador();
		Section section;
		for (Segmento segmento : transaccion.getSegmentos()) {
			section = procesaSegmento(body, segmento, apuntador);
			if(section!=null) {
				transaction.addSection(section);
			}
		}
		return transaction;
	}
	
	//*******/home/miguel/isban/tcpIsban.raw.xml
	private Section procesaSegmento(String body, Segmento segmento, Apuntador apuntador) {
		Section section = null;
		Field field = null;
		if(segmento.isIncluyeEtiquetas()) {
			for (Campo campo: segmento.getCampos()) {
				field = procesaCampoSegmentoConEtiquetas(body, campo, apuntador);
				if(field != null) {
					if(section == null) {
						section = new Section();
						section.setName(segmento.getIdentificador());
					}
					section.addField(field);
				}
			}

		} else {
			for (Campo campo: segmento.getCampos()) {
				field = procesaCampoSegmentoSinEtiquetas(body, campo, apuntador);
				if(field != null) {
					if(section == null) {
						section = new Section();
						section.setName(segmento.getIdentificador());
					}
					section.addField(field);
				}
			}
		}
		return section;	
	}
	
	private Field procesaCampoSegmentoSinEtiquetas(String body, Campo campo, Apuntador apuntador) {
		Field field = new Field();
		String valorCampo = body.substring(apuntador.getPosicion(), apuntador.getPosicion() + campo.getLongitud());
		apuntador.setPosicion(apuntador.getPosicion() + campo.getLongitud());
		field.setName(campo.getNombre());
		field.setValue(valorCampo);
		return field;
	}
	
	private Field procesaCampoSegmentoConEtiquetas(String body, Campo campo, Apuntador apuntador) {
		Field field = null;
		System.out.println("Posicion actual: " + apuntador.getPosicion());
		if((apuntador.getPosicion() + TAMANO_ETIQUETA) < body.length()) {
			String etiquetaCampo = body.substring(apuntador.getPosicion(), apuntador.getPosicion() + TAMANO_ETIQUETA);
			System.out.println("Campo: " + etiquetaCampo);
			if(campo.getNombre().equalsIgnoreCase("Campo-" + etiquetaCampo)) {
				field = new Field();
				field.setName(campo.getNombre());
				int longitud = Integer.parseInt(body.substring(apuntador.getPosicion() + TAMANO_ETIQUETA, apuntador.getPosicion() + TAMANO_ETIQUETA + TAMANO_LONGITUD));
				System.out.println("Longitud: " + longitud);
				String valorCampo = body.substring(apuntador.getPosicion() + TAMANO_ETIQUETA + TAMANO_LONGITUD, apuntador.getPosicion() + TAMANO_ETIQUETA + TAMANO_LONGITUD + longitud);	
				apuntador.setPosicion(apuntador.getPosicion() + TAMANO_ETIQUETA + TAMANO_LONGITUD + longitud);
				field.setValue(valorCampo);
			}
		}
		return field;
	}

	@Override
	public void updateRequest(TestExec testExec, Request request) {
		Transaction t = procesaTransaccion(INTLBuilder.buildRequestINTL11SinAutenticacion(), request.getBodyAsString());
		request.setBody(convierteTransaccion(t));
		request.setOperation(t.getName());
		super.updateRequest(testExec, request);
		System.out.println(request.getBodyAsString());
	}

	@Override
	public void updateResponse(TestExec testExec, Response response) {
		Transaction t = procesaTransaccion(INTLBuilder.buildResponseINTL11SinAutenticacion(), response.getBodyAsString());
		System.out.println("Response before: " + response.getBodyAsString());
		response.setBody(convierteTransaccion(t));
		super.updateResponse(testExec, response);
		System.out.println("Response after: " + response.getBodyAsString());
	}

	@Override
	public void updateResponse(TestExec testExec, TransientResponse response) {
		super.updateResponse(testExec, response);
	}
	
	private String convierteTransaccion(Transaction t) {
		String xmlBody = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Transaction.class, Section.class);
			
			StringWriter sw = new StringWriter();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			JAXBElement<Transaction> jaxbElement = new JAXBElement<Transaction>(new QName(null, t.getName()), Transaction.class, t);
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.marshal(jaxbElement, sw);

			xmlBody = sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlBody;
	}
	
	public static void main(String[] args) {
		BuroDeCreditoInt11Handler handler = new BuroDeCreditoInt11Handler(); 
		String stringHeader = "INTL110014000015265000101540001007MX0000ZM11001008VASmuDecICCMX000000000SP01     0000000PN05AVINA0016NO PROPORCIONADO0211ROSA ALICIA0513ROVE521205QWQPA1827 SUR 1111 123 NA0106CENTRO0221ZIHUATANEJO DE AZUETA0306MEXICO0403GRO0505408801001HES05002520002**";
		Transaction t = handler.procesaTransaccion(INTLBuilder.buildRequestINTL11SinAutenticacion(), stringHeader);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Transaction.class, Section.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			JAXBElement<Transaction> jaxbElement = new JAXBElement<Transaction>(new QName(null, t.getName()), Transaction.class, t);
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(jaxbElement, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
