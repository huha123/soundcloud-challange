package com.dev.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Queue;

import com.dev.event.Event;
import com.dev.event.EventType;
import com.dev.event.factory.BroadcastFactory;
import com.dev.event.factory.FollowFactory;
import com.dev.event.factory.PrivateMessageFactory;
import com.dev.event.factory.StatusUpdateFactory;
import com.dev.event.factory.UnfollowFactory;

public class EventProcess implements Process {
	private final Queue<Event> eventQueue;

	public EventProcess(Queue<Event> eventQueue) {
		this.eventQueue = eventQueue;
	}
	
	@Override
	public void process(Socket socket) throws IOException {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
		String message;
		while ((message = reader.readLine()) != null) {
			final String[] splitMessage = message.split("\\|");
			final String type = splitMessage[1];
			final Event event;
			
			switch (EventType.valueOf(type)) {
				case F:
					event = new FollowFactory().create(type, splitMessage, message);
					eventQueue.add(event);
					break;
				case U:
					event = new UnfollowFactory().create(type, splitMessage, message);
					eventQueue.add(event);
					break;
				case B:
					event = new BroadcastFactory().create(type, splitMessage, message);
					eventQueue.add(event);
					break;
				case P:
					event = new PrivateMessageFactory().create(type, splitMessage, message);
					eventQueue.add(event);
					break;
				case S:
					event = new StatusUpdateFactory().create(type, splitMessage, message);
					eventQueue.add(event);
					break;
			}
		}
	}

}
