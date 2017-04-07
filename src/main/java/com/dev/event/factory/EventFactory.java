package com.dev.event.factory;

import com.dev.event.Event;

public interface EventFactory {
	
	public Event create(String type, String[] splitMessage, String message);
	
}
