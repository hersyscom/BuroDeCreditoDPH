package com.ca.buro;

import com.ca.buro.layout.Field;
import com.ca.buro.layout.Section;
import com.ca.buro.layout.Template;
import com.ca.buro.model.Message;
import com.ca.buro.model.MessageField;
import com.ca.buro.model.MessageSection;

public class MessageProcessor {

	private MessageFactory messageFactory;
	private static final int LABEL_LENGTH = 2;
	private static final int FIELD_SIZE_LENGTH = 2;

	public MessageFactory getMessageFactory() {
		return messageFactory;
	}

	public void setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}
	
	public Message processMessage(Template template, String body) {
		Message message = messageFactory.createMessage();
		message.setName(template.getName());
		Apuntador apuntador = new Apuntador();
		MessageSection messageSection;
		for (Section section : template.getSections()) {
			messageSection = processMessageSection(body, section, apuntador);
			if (messageSection != null) {
				message.addSection(messageSection);
			}
		}
		return message;
	}
	
	private MessageSection processMessageSection(String body, Section section, Apuntador apuntador) {
		MessageSection messageSection = null;
		MessageField messageField = null;

		for (Field field: section.getFields()) {
			if(section.isLabelIncluded()) {
				messageField = processMessageFieldLabelIncluded(body, field, apuntador);
			} else {
				messageField = processMessageField(body, field, apuntador);
			}
			if(messageField != null) {
				if(messageSection == null) {
					messageSection = messageFactory.createSection();
					messageSection.setName(section.getId());
				}
				messageSection.addField(messageField);
			}
		}

		return messageSection;	
	}
	
	private MessageField processMessageField(String body, Field field, Apuntador apuntador) {
		MessageField messageField = messageFactory.createField();
		String fieldValue = body.substring(apuntador.getPosicion(), apuntador.getPosicion() + field.getLength());
		apuntador.setPosicion(apuntador.getPosicion() + field.getLength());
		messageField.setName(field.getName());
		messageField.setValue(fieldValue);
		return messageField;
	}
	
	private MessageField processMessageFieldLabelIncluded(String body, final Field field, Apuntador apuntador) {
		MessageField messageField = null;
		if((apuntador.getPosicion() + LABEL_LENGTH) < body.length()) {
			String fieldLabel = body.substring(apuntador.getPosicion(), apuntador.getPosicion() + LABEL_LENGTH);
			if(field.getName().equalsIgnoreCase("Campo-" + fieldLabel)) {
				messageField = messageFactory.createField();
				messageField.setName(field.getName());
				int length = Integer.parseInt(body.substring(apuntador.getPosicion() + LABEL_LENGTH, apuntador.getPosicion() + LABEL_LENGTH + FIELD_SIZE_LENGTH));
				String fieldValue = body.substring(apuntador.getPosicion() + LABEL_LENGTH + FIELD_SIZE_LENGTH, apuntador.getPosicion() + LABEL_LENGTH + FIELD_SIZE_LENGTH + length);	
				apuntador.setPosicion(apuntador.getPosicion() + LABEL_LENGTH + FIELD_SIZE_LENGTH + length);
				messageField.setValue(fieldValue);
			}
		}
		return messageField;
	}
}
