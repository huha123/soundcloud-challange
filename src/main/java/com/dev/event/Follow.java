package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class Follow extends Event {
	private final int seq;
	private final String[] splitMessage;
	private final String message;
	
	public Follow(int seq, String[] splitMessage, String message) {
		super(seq);
		this.seq = seq;
		this.splitMessage = splitMessage;
		this.message = message;
	}

	@Override
	public void notifyUser(Map<Integer, User> clients) {
		String toUserId = splitMessage[3];
		User user = clients.get(toUserId);
		if (user != null) {
			user.notifyUser(toUserId);
		}
	}

}
