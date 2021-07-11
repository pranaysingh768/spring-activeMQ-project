package com.spring.activeMq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.activeMq.receiver.MessageReceiver;
import com.spring.activeMq.sender.MessageSender;

@RestController
public class ActiveMqController {

	// http://localhost:8161/
	
	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	private MessageReceiver messageReceiver;
	
	@PostMapping("/producer")
	public Map<String,String> producer(@RequestBody Map<String,String> parameter) {
		System.out.println("hhhhhhhh");
		Map<String,String> map = new HashMap<>();
		messageSender.sendMessage(parameter.get("message"));
		map.put("key", "success");
		return map;
	}
	
	@GetMapping("/consumer")
	public Map<String,String> consumer() {
		Map<String,String> map = new HashMap<>();
		map.put("key", messageReceiver.receiveMessage());
		return map;
	}
	
}
