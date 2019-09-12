package br.com.bots.controller;

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

import br.com.bots.entity.BotEntity;
import br.com.bots.service.impl.BotServiceImpl;

@RestController
@RequestMapping(value = "/bots")
public class BotsController {

	@Autowired(required=true)
	private BotServiceImpl service;
	
	@GetMapping("/{id}")
	public ResponseEntity<BotEntity> getBots(@PathVariable String id) {
		Optional<BotEntity> bot = service.findBotById(id);
		if(bot.isPresent()) {
			return ResponseEntity.ok(bot.get());	
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<BotEntity> createBots(@RequestBody BotEntity bot) {
		service.save(bot);
		return ResponseEntity.ok(bot);
	}
	
	@PutMapping
	public ResponseEntity<BotEntity> updateBots(@RequestBody BotEntity bot) {
		service.update(bot);
		return ResponseEntity.ok(bot);
	}
	
	@DeleteMapping
	public ResponseEntity deleteBots(@RequestBody BotEntity bot) {
		service.delete(bot);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
}
