package br.com.bots.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.bots.entity.BotEntity;

public interface BotsRepository extends MongoRepository<BotEntity, String>{

}
