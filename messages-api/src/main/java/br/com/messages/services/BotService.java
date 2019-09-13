package br.com.messages.services;

import java.util.Optional;

import br.com.messages.entities.BotEntity;

public interface BotService {

	Optional<BotEntity> findBotById(String id);
	
	void save(BotEntity bot) throws Exception;
	
	void delete(BotEntity bot) throws Exception;
	
}
