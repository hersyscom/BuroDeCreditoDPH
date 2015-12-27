package com.ca.buro;

import com.ca.buro.model.Message;
import com.ca.buro.model.MessageField;
import com.ca.buro.model.MessageSection;
import com.ca.buro.model.ResponseField;
import com.ca.buro.model.ResponseMessage;
import com.ca.buro.model.ResponseSection;

public class ResponseMessageFactory implements MessageFactory{

	public Message createMessage() {
		return new ResponseMessage();
	}

	public MessageSection createSection() {
		return new ResponseSection();
	}

	public MessageField createField() {
		return new ResponseField();
	}
}
