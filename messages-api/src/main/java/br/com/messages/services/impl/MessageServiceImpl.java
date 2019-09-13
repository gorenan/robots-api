package br.com.messages.services.impl;

import java.util.Date;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.messages.entities.MessageEntity;
import br.com.messages.repositories.MessageRepository;
import br.com.messages.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository repository;
	
	public Optional<MessageEntity> getMessageById(String id) {
		Optional<MessageEntity> message = null;
		try {
			message = repository.findById(id);	
		} catch (Exception e) {
			e.printStackTrace();
			return message;
		}
		return message;
	}

	public Iterable<MessageEntity> getMessagesByConversationId(String id) {
		Iterable<MessageEntity> messages = null;
		try {
			messages = repository.findByConversationId(id);	
		} catch (Exception e) {
			e.printStackTrace();
			return messages;
		}
		return messages;
	}

	public void createMessage(MessageEntity message) throws Exception {
		if(isMessageValid(message)){
				try {
				if(message.getConversationId() == null || message.getConversationId().equals("") ) {
					message.setConversationId(new ObjectId().toString());	
				}
				message.setTimestamp(new Date());
				repository.save(message);
			} catch (Exception e) {
				 e.printStackTrace();
			}
		} else {
			throw new Exception("Dados Invalidos");
		}
	}

	private boolean isMessageValid(MessageEntity message) {
		if(message.getFrom() == null || message.getFrom().equals("") || 
			message.getTo() == null || message.getTo().equals("") ||
			message.getText() == null || message.getText().equals("")) {
			return false;	
		} 
		return true;
	}

}
