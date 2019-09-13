package br.com.messages.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.messages.entities.MessageEntity;
import br.com.messages.services.impl.MessageServiceImpl;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

	@Autowired
	private MessageServiceImpl service;
	
	@GetMapping("/{id}")
	public ResponseEntity<MessageEntity> getMessageById(@PathVariable String id){
		Optional<MessageEntity> message = null;
		try {
			message = service.getMessageById(id);
			if(!message.isPresent()) {
				return ResponseEntity.noContent().build();	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(message.get());
	}
	
	@GetMapping
	public ResponseEntity<Iterable<MessageEntity>> getMessagesBydConversationId(@RequestParam("conversationId") String id){
		Iterable<MessageEntity> messages = null;
		try {
			messages = service.getMessagesByConversationId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(messages);
	}
	
	@PostMapping
	public ResponseEntity<MessageEntity> createMessage(@RequestBody MessageEntity message){
		try {
			service.createMessage(message);	
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(message);
	}
}
