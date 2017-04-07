package com.dev.server;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import com.dev.event.Event;

public class EventSortServer extends ServerAbs {
	private final Queue<Event> eventQueue;
	private final Queue<Event> eventQueueOrdering;
	private final AtomicInteger atomicInteger = new AtomicInteger(1);
	
	public EventSortServer(Queue<Event> eventQueue, Queue<Event> eventQueueOrdering) {
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
