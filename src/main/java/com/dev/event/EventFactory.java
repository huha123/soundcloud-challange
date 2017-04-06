package com.dev.event;

public interface EventFactory {
	public Event create(String type, String[] splitMessage, String message);
	
}
