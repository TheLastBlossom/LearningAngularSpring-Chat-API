package com.ervinaldo.springboot.backend.chat.services;

import java.util.List;

import com.ervinaldo.springboot.backend.chat.models.docs.Message;

public interface IChatService {
	public List<Message> getLast10Messages();
	public Message save(Message message);
}
