package br.com.bots.service;

import java.util.Optional;

import br.com.bots.entity.BotEntity;

public interface BotService {

	Optional<BotEntity> findBotById(String id);
	
	void save(BotEntity bot);
	
	void delete(BotEntity bot);
	
}
