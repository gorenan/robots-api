package br.com.messages.services;

import java.util.Optional;

import br.com.messages.entities.MessageEntity;

public interface MessageService {
	
	Optional<MessageEntity> getMessageById(String id);
	
	Iterable<MessageEntity> getMessagesByConversationId(String id);
	
	void createMessage(MessageEntity message) throws Exception;
	
}
