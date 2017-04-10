package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class Follow extends Event {
	private final int seq;
	private final String toUserId;
	private final String fromUserId;
	private final String message;
	
	public Follow(int seq, String toUserId, String fromUserId, String message) {
		super(seq);
		this.seq = seq;
		this.toUserId = toUserId;
		this.fromUserId = fromUserId;
		this.message = message;
	}

	@Override
	public void sendMessage(Map<Integer, User> clients) {
		User user = clients.get(toUserId);
		User fromUser = clients.get(fromUserId);
		if (user != null) {
			System.out.println("[Follow][" + seq + "]:" + message);
			boolean isFollow = user.notifyUser(message);
			System.out.println("isFollow>>>>>>>>>>>>>>>>>>>>>>>" + isFollow);
			user.addFollower(fromUser);
		}
	}
}
