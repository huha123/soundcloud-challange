package com.dev.event;

public class EventFactoryImpl implements EventFactory {

	@Override
	public Event create(String type, String[] splitMessage, String message) {
		final int seq = Integer.parseInt(splitMessage[0]);
		if (type.equals("F")) {
			return new Follow(seq, splitMessage, message);
			
		} else if (type.equals("U")) {
			return new Unfollow(seq, splitMessage, message);
			
		} else if (type.equals("B")) {
			return new Broadcast(seq, splitMessage, message);
			
		} else if (type.equals("P")) {
			return new PrivateMessage(seq, splitMessage, message);
			
		} else if (type.equals("S")) {
			return new StatusUpdate(seq, splitMessage, message);
		}
		return null;
	}

}
