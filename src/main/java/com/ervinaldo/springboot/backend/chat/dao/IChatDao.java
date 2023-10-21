package com.ervinaldo.springboot.backend.chat.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ervinaldo.springboot.backend.chat.models.docs.Message;

public interface IChatDao extends MongoRepository<Message, String>{
	public List<Message> findFirst10ByOrderByDateDesc();
}
