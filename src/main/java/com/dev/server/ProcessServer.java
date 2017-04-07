package com.dev.server;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import com.dev.event.Event;
import com.dev.user.User;

public class ProcessServer extends ServerAbs {
	private final Map<Integer, User> clients;
	private final Queue<Event> eventQueue;
	private final Queue<Event> eventQueueOrdering;
	private final AtomicInteger atomicInteger = new AtomicInteger(1);
	
	public ProcessServer(Map<Integer, User> clients, Queue<Event> eventQueue, Queue<Event> eventQueueOrdering) {
		this.clients = clients;
		this.eventQueue = eventQueue;
		this.eventQueueOrdering = eventQueueOrdering;
	}
	
	@Override
	public void run() {
		while (true) {
			final Event event = eventQueue.peek();
			if (event != null && event.getSeq() <= atomicInteger.get()) {
				System.out.println("ProcessServer[" + event.getSeq() + "][" + atomicInteger.get() +"] :" + event.getClass());
				final Event eventOrdering = eventQueue.poll();
				if (eventQueueOrdering.add(eventOrdering)) {
					atomicInteger.getAndIncrement();
				}
			}
		}
	}

}
