package com.ervinaldo.springboot.backend.chat.controllers;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ervinaldo.springboot.backend.chat.models.docs.Message;
import com.ervinaldo.springboot.backend.chat.services.ChatServiceImp;

@Controller
public class ChatController {
	@Autowired
	private ChatServiceImp chatservice;
	@Autowired
	private SimpMessagingTemplate webSocket;

	private String[] colors = { "red", "green", "blue", "magenta", "purple", "orange" };

	@MessageMapping("/message")
	@SendTo("/chat/message")
	public Message receiveMessage(Message message) {
		message.setDate(new Date().getTime());
		if (message.getType().equals("NEW_USER")) {
			message.setColor(colors[new Random().nextInt(colors.length)]);
			message.setText(message.getUsername());
		} else {
			chatservice.save(message);
		}
		return message;
	}

	@MessageMapping("/typing")
	@SendTo("/chat/typing")
	public String isTyping(String username) {
		return username.concat(" is writing...");
	}

	@MessageMapping("/history")
	public void history(String clientId) {
		webSocket.convertAndSend("/chat/history" + clientId, chatservice.getLast10Messages());
	}

}
