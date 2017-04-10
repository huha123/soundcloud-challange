package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class Unfollow extends Event {
	private final int seq;
	private final String fromUserId;
	private final String toUserId;
	private final String message;
	
	public Unfollow(int seq, String fromUserId, String toUserId, String message) {
		super(seq);
		this.seq = seq;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.message = message;
	}

	@Override
	public void sendMessage(Map<Integer, User> clients) {
		System.out.println("[Unfollow][" + seq + "]:" + message);
		User user = clients.get(toUserId);
		User fromUser = clients.get(fromUserId);
		user.removeFollower(fromUser);
	}
}
