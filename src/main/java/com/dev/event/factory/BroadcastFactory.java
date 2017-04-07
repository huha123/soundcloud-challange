package com.dev.event.factory;

import com.dev.event.Broadcast;
import com.dev.event.Event;

public class BroadcastFactory implements EventFactory {

	@Override
	public Event create(String type, String[] splitMessage, String message) {
		final int seq = Integer.parseInt(splitMessage[0]);
		return new Broadcast(seq, message);
	}

}
