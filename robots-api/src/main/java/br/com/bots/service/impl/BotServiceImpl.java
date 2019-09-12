package br.com.bots.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bots.entity.BotEntity;
import br.com.bots.repository.BotsRepository;
import br.com.bots.service.BotService;

@Service
public class BotServiceImpl implements BotService {

	@Autowired
	private BotsRepository repository;
	
	public Optional<BotEntity> findBotById(String id) {
		Optional<BotEntity> bot = null;
		try {
			bot = repository.findById(id);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bot ;
	}

	public void save(BotEntity bot) {
		try {
			repository.save(bot);	
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

	public void delete(BotEntity bot) {
		try {
			repository.delete(bot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(BotEntity bot) {
		try {
			repository.save(bot);	
		} catch (Exception e) {
			 e.printStackTrace();
		}	
	}
}
