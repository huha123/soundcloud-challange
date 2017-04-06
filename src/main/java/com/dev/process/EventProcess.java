package com.dev.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Queue;

import com.dev.event.Event;
import com.dev.event.EventFactory;
import com.dev.event.EventFactoryImpl;

public class EventProcess implements Process {
	private final Queue<Event> eventQueue;
	private final EventFactory eventFactory = new EventFactoryImpl();
	
	public EventProcess(Queue<Event> eventQueue) {
		this.eventQueue = eventQueue;
	}
	
	@Override
	public void process(Socket socket) throws IOException {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
		String message;
		while ((message = reader.readLine()) != null) {
//			System.out.println("event message:" + message);
			String[] splitMessage = message.split("\\|");
			String type = splitMessage[1];
			final Event event = eventFactory.create(type, splitMessage, message);
			eventQueue.add(event);
		}
	}

}
