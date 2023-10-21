package com.ervinaldo.springboot.backend.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//EndPoint to connect the client with the broker
		registry.addEndpoint("/chat-websocket").setAllowedOriginPatterns("http://localhost:4200")
		.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {		
		//Register the prefix for events, which means you must always add this prefix when you want to subscribe to an event.
		//Every event of the broker must add this prefix in the @SendTo annotation
		registry.enableSimpleBroker("/chat/");
		//Register prefix for destinations, which means you should always add this prefix when you want to send something to the destination/broker
		registry.setApplicationDestinationPrefixes("/app");
	}

}
