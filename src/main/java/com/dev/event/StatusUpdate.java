package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class StatusUpdate extends Event{
	private final int seq;
	private final int fromUserId;
	private final String message;
	
	public StatusUpdate(int seq, int fromUserId, String message) {
		super(seq);
		this.seq = seq;
		this.fromUserId = fromUserId;
		this.message = message;
	}

	@Override
	public void sendMessage(Map<Integer, User> clients) {
		System.out.println("[StatusUpdate][" + seq + "]:" + message);		
		if (clients.containsKey(fromUserId)) {
			User fromUser = clients.get(fromUserId);
			fromUser.notifyAllUser(message);
		}
	}

	@Override
	public String toString() {
		return "StatusUpdate [seq=" + seq + ", fromUserId=" + fromUserId + ", message=" + message + "]";
	}

}
