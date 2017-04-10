package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class StatusUpdate extends Event{
	private final int seq;
	private final String fromUserId;
	private final String message;
	
	public StatusUpdate(int seq, String fromUserId, String message) {
		super(seq);
		this.seq = seq;
		this.fromUserId = fromUserId;
		this.message = message;
	}

	@Override
	public void sendMessage(Map<Integer, User> clients) {
		User fromUser = clients.get(fromUserId);
		fromUser.notifyAllUser(message);
		System.out.println("[StatusUpdate][" + seq + "]:" + message);		
	}

}
