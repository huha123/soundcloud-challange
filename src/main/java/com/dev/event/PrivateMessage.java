package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class PrivateMessage extends Event {
	private final int seq;
	private final String toUserId;
	private final String message;
	
	public PrivateMessage(int seq, String toUserId, String message) {
		super(seq);
		this.seq = seq;
		this.toUserId = toUserId;
		this.message = message;
	}

	@Override
	public void sendMessage(Map<Integer, User> clients) {
		User user = clients.get(toUserId);
		if (user != null) {
			System.out.println("[PrivateMessage][" + seq + "]:" + message);
			user.notifyUser(message);
		}
	}

}
