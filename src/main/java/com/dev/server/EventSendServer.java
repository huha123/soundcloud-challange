package com.dev.server;

import java.util.Map;
import java.util.Queue;

import com.dev.event.Event;
import com.dev.user.User;

public class EventSendServer extends ServerAbs {
	final Map<Integer, User> clients;
	final Queue<Event> eventQueueOrdering;
	
	public EventSendServer(Map<Integer, User> clients, Queue<Event> eventQueueOrdering) {
		this.clients = clients;
		this.eventQueueOrdering = eventQueueOrdering;
	}
	
	@Override
	public void run() { 
		while (true) {
			if (eventQueueOrdering.size() > 0) {
				final Event event = eventQueueOrdering.poll();
				event.sendMessage(clients);
				System.out.println("eventQueueOrdering.size()>>>" + eventQueueOrdering.size());
			}
		}
	}
}
