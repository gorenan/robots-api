package br.com.messages.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.messages.entities.MessageEntity;

public interface MessageRepository extends MongoRepository<MessageEntity, String>{

	
	public Iterable<MessageEntity> findByConversationId(String conversationId);
	
}
