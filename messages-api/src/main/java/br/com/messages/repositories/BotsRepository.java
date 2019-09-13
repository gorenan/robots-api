package br.com.messages.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.messages.entities.BotEntity;

public interface BotsRepository extends MongoRepository<BotEntity, String>{

}
