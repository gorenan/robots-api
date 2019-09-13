package br.com.messages.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.messages.entities.BotEntity;
import br.com.messages.services.impl.BotServiceImpl;

@RestController
@RequestMapping(value = "/bots")
public class BotsController {

	@Autowired(required=true)
	private BotServiceImpl service;
	
	@GetMapping("/{id}")
	public ResponseEntity<BotEntity> getBots(@PathVariable String id) {
		Optional<BotEntity> bot = null;
		try {
			bot = service.findBotById(id);
			if(!bot.isPresent()) {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(bot.get());
		
		
	}
	
	@PostMapping
	public ResponseEntity<BotEntity> createBots(@RequestBody BotEntity bot) {
		try {
			service.save(bot);	
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(bot);
	}
	
	@PutMapping
	public ResponseEntity<BotEntity> updateBots(@RequestBody BotEntity bot) {
		try {
			service.save(bot);	
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(bot);
	}
	
	@DeleteMapping
	public ResponseEntity<Object> deleteBots(@RequestBody BotEntity bot) {
		try {
			service.delete(bot);	
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().build();
	}
	
	
}
