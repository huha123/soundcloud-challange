package com.dev.server;

import java.util.Map;
import java.util.Queue;

import com.dev.event.Event;
import com.dev.user.User;

public class ProcessServer extends ServerAbs {
	private final Map<Integer, User> clients;
	private final Queue<Event> eventQueue;
	
	public ProcessServer(Map<Integer, User> clients, Queue<Event> eventQueue) {
		this.clients = clients;
		this.eventQueue = eventQueue;
	}
	
	@Override
	public void run() {
		while (true) {
			Event event = eventQueue.poll();
			if (event != null) {
				System.out.println("ProcessServer[" + event.getSeq() + "] :" + event.toString());
			}
		}
	}

}
