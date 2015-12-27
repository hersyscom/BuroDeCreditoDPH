package com.ca.buro;

import com.ca.buro.model.Message;
import com.ca.buro.model.MessageField;
import com.ca.buro.model.MessageSection;
import com.ca.buro.model.RequestMessage;
import com.ca.buro.model.RequestSection;

public class RequestMessageFactory implements MessageFactory {

	public Message createMessage() {
		return new RequestMessage();
	}

	public MessageSection createSection() {
		return new RequestSection();
	}

	public MessageField createField() {
		return new MessageField();
	}
}
