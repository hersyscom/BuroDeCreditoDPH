package com.ca.buro;

import com.ca.buro.model.Message;
import com.ca.buro.model.MessageField;
import com.ca.buro.model.MessageSection;

public interface MessageFactory {

	public Message createMessage();
	
	public MessageSection createSection();
	
	public MessageField createField();
}
