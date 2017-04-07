package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class Broadcast extends Event {
	private final int seq;
	private final String[] splitMessage;
	private final String message;
	
	public Broadcast(int seq, String[] splitMessage, String message) {
		super(seq);
		this.seq = seq;
		this.splitMessage = splitMessage;
		this.message = message;
	}

	@Override
	public void notifyUser(Map<Integer, User> clients) {
		System.out.println("Broadcast [" + seq + "][" + splitMessage[1] + "] :" + message);
		for (User user : clients.values()) {
			user.notifyUser(message);
		}
	}

}
