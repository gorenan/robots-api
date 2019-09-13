package br.com.messages.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.messages.entities.BotEntity;
import br.com.messages.repositories.BotsRepository;
import br.com.messages.services.BotService;

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

	public void save(BotEntity bot) throws Exception {
		if(bot.getName() != null || !bot.getName().equals("")) {
			try {
				repository.save(bot);
			} catch (Exception e) {
				 e.printStackTrace();
			}
		}else {
			throw new Exception("Dados Invalidos");
		}
		
	}

	public void delete(BotEntity bot) throws Exception {
		if(bot.getId() != null || !bot.getId().equals("")){
			try {
				repository.delete(bot);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else {
			throw new Exception("Dados Invalidos");
		}
		
	}

	public void update(BotEntity bot) throws Exception {
		if(bot.getId() != null || !bot.getId().equals("")){
			try {
				repository.save(bot);	
			} catch (Exception e) {
				 e.printStackTrace();
			}	
		}else {
			throw new Exception("Dados Invalidos");
		}
	}
}
