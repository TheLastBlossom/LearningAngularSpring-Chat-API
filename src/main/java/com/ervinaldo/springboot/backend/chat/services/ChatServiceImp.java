package com.ervinaldo.springboot.backend.chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ervinaldo.springboot.backend.chat.dao.IChatDao;
import com.ervinaldo.springboot.backend.chat.models.docs.Message;
@Service
public class ChatServiceImp implements IChatService {
	@Autowired
	private IChatDao chatdao;
	
	@Override
	public List<Message> getLast10Messages() {
		// TODO Auto-generated method stub
		return chatdao.findFirst10ByOrderByDateDesc();
	}

	@Override
	public Message save(Message message) {
		// TODO Auto-generated method stub
		return chatdao.save(message);
	}

}
