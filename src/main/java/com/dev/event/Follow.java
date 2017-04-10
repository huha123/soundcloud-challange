package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class Follow extends Event {
	private final int seq;
	private final int toUserId;
	private final int fromUserId;
	private final String message;
	
	public Follow(int seq, int toUserId, int fromUserId, String message) {
		super(seq);
		this.seq = seq;
		this.toUserId = toUserId;
		this.fromUserId = fromUserId;
		this.message = message;
	}

	@Override
	public void sendMessage(Map<Integer, User> clients) {
		if (clients.containsKey(toUserId)) {
			System.out.println("[Follow][" + seq + "]:" + message);
			User user = clients.get(toUserId);
			user.notifyUser(message);
			
			if (clients.containsKey(fromUserId)) {
				User fromUser = clients.get(fromUserId);
				user.addFollower(fromUser);
			}
		}
	}

	@Override
	public String toString() {
		return "Follow [seq=" + seq + ", toUserId=" + toUserId + ", fromUserId=" + fromUserId + ", message=" + message
				+ "]";
	}
}
