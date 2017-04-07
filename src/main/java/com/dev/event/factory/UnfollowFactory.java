package com.dev.event.factory;

import com.dev.event.Event;
import com.dev.event.Unfollow;

public class UnfollowFactory implements EventFactory {

	@Override
	public Event create(String type, String[] splitMessage, String message) {
		final int seq = Integer.parseInt(splitMessage[0]);
		final String fromUserId = splitMessage[2];
		final String toUserId = splitMessage[3];
		return new Unfollow(seq, fromUserId, toUserId, message);
	}

}
