package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class PrivateMessage extends Event {
	private final int seq;
	private final int toUserId;
	private final String message;
	
	public PrivateMessage(int seq, int toUserId, String message) {
		super(seq);
		this.seq = seq;
		this.toUserId = toUserId;
		this.message = message;
	}

	@Override
	public void sendMessage(Map<Integer, User> clients) {
		if (clients.containsKey(toUserId)) {
			System.out.println("[PrivateMessage][" + seq + "]:" + message);
			final User user = clients.get(toUserId);
			user.notifyUser(message);
		}
	}

	@Override
	public String toString() {
		return "PrivateMessage [seq=" + seq + ", toUserId=" + toUserId + ", message=" + message + "]";
	}

}
