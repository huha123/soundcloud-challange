package com.dev.event.factory;

import com.dev.event.Event;
import com.dev.event.StatusUpdate;

public class StatusUpdateFactory implements EventFactory {

	@Override
	public Event create(String type, String[] splitMessage, String message) {
		final int seq = Integer.parseInt(splitMessage[0]);
		final int fromUserId = Integer.parseInt(splitMessage[2]);
		return new StatusUpdate(seq, fromUserId, message);
	}

}
