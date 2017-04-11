package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class Unfollow extends Event {
	private final int seq;
	private final int fromUserId;
	private final int toUserId;
	private final String message;
	
	public Unfollow(int seq, int fromUserId, int toUserId, String message) {
		super(seq);
		this.seq = seq;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.message = message;
	}

	@Override
	public void sendMessage(Map<Integer, User> clients) {
		System.out.println("[Unfollow][" + seq + "]:" + message);
		if (clients.containsKey(toUserId)) {
			final User user = clients.get(toUserId);
			if (clients.containsKey(fromUserId)) {
				final User fromUser = clients.get(fromUserId);
				user.removeFollower(fromUser);
			}
		}
	}

	@Override
	public String toString() {
		return "Unfollow [seq=" + seq + ", fromUserId=" + fromUserId + ", toUserId=" + toUserId + ", message=" + message
				+ "]";
	}
	
}
