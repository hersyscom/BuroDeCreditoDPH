package com.ca.buro;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.buro.model.Message;
import com.ca.buro.model.MessageField;
import com.ca.buro.model.RequestMessage;
import com.ca.buro.model.RequestSection;
import com.ca.buro.model.ResponseField;
import com.ca.buro.model.ResponseMessage;
import com.ca.buro.model.ResponseSection;
import com.itko.lisa.test.TestExec;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.Response;
import com.itko.lisa.vse.stateful.protocol.DataProtocol;

public class BuroDeCreditoInt11Handler extends DataProtocol {
	
	private static final Logger logger = LoggerFactory.getLogger(BuroDeCreditoInt11Handler.class);
	private static final String REQUEST_TO_XML_ERROR = "Error al convertir la peticion a XML.";
	private static final String RESPONSE_TO_XML_ERROR = "Error al convertir la respuesta a XML.";
	private static final String XML_TO_RESPONSE_ERROR = "Error al convertir el documento XML a respuesta";
	
	private INTLBuilder intlBuilder = INTLBuilder.getInstance();

	private String requestMessageToXML(RequestMessage requestMessage) {
		String xmlBody = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RequestMessage.class, RequestSection.class, MessageField.class);
			
			StringWriter sw = new StringWriter();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			JAXBElement<RequestMessage> jaxbElement = new JAXBElement<RequestMessage>(new QName(null, requestMessage.getName()), RequestMessage.class, requestMessage);
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.marshal(jaxbElement, sw);
			
			xmlBody = sw.toString();
		} catch (JAXBException e) {
			logger.error(REQUEST_TO_XML_ERROR, e);
		}
		return xmlBody;
	}
	
	private String responseMessageToXML(ResponseMessage responseMessage) {
		String xmlBody = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ResponseMessage.class, ResponseSection.class, ResponseField.class);
			
			StringWriter sw = new StringWriter();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.marshal(responseMessage, sw);
			
			xmlBody = sw.toString();
		} catch (JAXBException e) {
			logger.error(RESPONSE_TO_XML_ERROR, e);
		}
		return xmlBody;
	}
	
	private ResponseMessage xmlToResponseMessage(String xml) {
		ResponseMessage responseMessage  = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ResponseMessage.class, ResponseSection.class, ResponseField.class);
			StringReader sr = new StringReader(xml);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			responseMessage = (ResponseMessage) unmarshaller.unmarshal(sr);
			return responseMessage;
		} catch (JAXBException e) {
			logger.error(XML_TO_RESPONSE_ERROR, e);
		}
		return responseMessage;
	}

	@Override
	public void updateRequest(TestExec testExec, Request request) {
		MessageProcessor messageProcessor = new MessageProcessor();
		messageProcessor.setMessageFactory(new RequestMessageFactory());
		
		Message requestMessage = messageProcessor.processMessage(intlBuilder.buildRequestINTL11SinAutenticacion(), request.getBodyAsString());
		request.setBody(requestMessageToXML((RequestMessage)requestMessage));
		request.setOperation(requestMessage.getName());
	}

	@Override
	public void updateResponse(TestExec testExec, Response response) {
		MessageProcessor messageProcessor = new MessageProcessor();
		messageProcessor.setMessageFactory(new ResponseMessageFactory());
		
		Message responseMessage = messageProcessor.processMessage(intlBuilder.buildResponseINTL11SinAutenticacion(), response.getBodyAsString());
		response.setBody(responseMessageToXML((ResponseMessage)responseMessage));
		response.setBinary(false);
	}

//	@Override
//	public void updateResponse(TestExec testExec, TransientResponse response) {
//		Transaction t = procesaTransaccion(INTLBuilder.buildResponseINTL11SinAutenticacion(), response.getBodyAsString());
//		response.setBody(convierteTransaccion(t));
//		response.setBinary(false);
//	}
	
	public static void main(String[] args) {
		BuroDeCreditoInt11Handler handler = new BuroDeCreditoInt11Handler();
		
		MessageProcessor messageProcessor = new MessageProcessor();
		messageProcessor.setMessageFactory(new RequestMessageFactory());

		String stringHeader = "INTL110014000015265000101540001007MX0000ZM11001008VASmuDecICCMX000000000SP01     0000000PN05AVINA0016NO PROPORCIONADO0211ROSA ALICIA0513ROVE521205QWQPA1827 SUR 1111 123 NA0106CENTRO0221ZIHUATANEJO DE AZUETA0306MEXICO0403GRO0505408801001HES05002520002**";
		
		Message requestMessage = messageProcessor.processMessage(handler.intlBuilder.buildRequestINTL11SinAutenticacion(), stringHeader);
		logger.debug(handler.requestMessageToXML((RequestMessage)requestMessage));
		
		stringHeader = "INTL110014000015264000101100001MX0000ZM1100100800PN06FLORES0005GOMEZ0205JESUS0308SALVADOR0513GALF600331WKIPA28RCDA DE OLIVOS 3 D 456K 123Q0203ZAC0403ZAC0505980601001H120822092015IQ08220920150110ZM110010080215BANCO SANTANDER0402CC060100701I0801YRS08220920150002000102000202000302000402000502000602000702000802000904000010040000110400001204000013040000140400001502001602001701Y1805NNNNN1901N310200320200330200340800000000350800000000360200370800000000380200390800000000400200410800000000SC08BC SCORE00030070104-009ES050054000097578416490102**";
		
		messageProcessor.setMessageFactory(new ResponseMessageFactory());
		
		Message responseMessage = messageProcessor.processMessage(handler.intlBuilder.buildResponseINTL11SinAutenticacion(), stringHeader);
		stringHeader = (handler.responseMessageToXML((ResponseMessage) responseMessage));
		logger.debug(stringHeader);	
		responseMessage = handler.xmlToResponseMessage(stringHeader);
		stringHeader = (handler.responseMessageToXML((ResponseMessage) responseMessage));
		logger.debug(stringHeader);
	}
}
