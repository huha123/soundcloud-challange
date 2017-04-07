package com.dev.event.factory;

import com.dev.event.Event;
import com.dev.event.PrivateMessage;

public class PrivateMessageFactory implements EventFactory{

	@Override
	public Event create(String type, String[] splitMessage, String message) {
		final int seq = Integer.parseInt(splitMessage[0]);
		final String toUserId = splitMessage[3];
		return new PrivateMessage(seq, toUserId, message);
	}

}
