package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class Broadcast extends Event {
	private final int seq;
	private final String message;
	
	public Broadcast(int seq, String message) {
		super(seq);
		this.seq = seq;
		this.message = message;
	}

	@Override
	public void sendMessage(Map<Integer, User> clients) {
		for (User user : clients.values()) {
			System.out.println("[Broadcast][" + seq + "]:" + message);
			user.notifyUser(message);
		}
	}

	@Override
	public String toString() {
		return "Broadcast [seq=" + seq + ", message=" + message + "]";
	}
}
