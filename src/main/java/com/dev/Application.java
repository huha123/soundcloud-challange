package com.dev;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import com.dev.event.Event;
import com.dev.process.ClientProcess;
import com.dev.process.EventProcess;
import com.dev.server.EventSendServer;
import com.dev.server.EventSortServer;
import com.dev.server.Server;
import com.dev.server.ServerAbs;
import com.dev.user.User;

public class Application {
	private final ExecutorService executorService;
	private final List<ServerAbs> servers;
	
	public Application(ExecutorService executorService, List<ServerAbs> servers) {
		this.executorService = executorService;
		this.servers = servers;
	}
	
	public void start() {
		for (ServerAbs server : servers) {
			executorService.execute(server);
		}
	}
	
	public static void main(String[] args) throws IOException {
		final Map<Integer, User> clients = new ConcurrentHashMap<>();
		final PriorityBlockingQueue<Event> eventQueue = new PriorityBlockingQueue<>();
		final PriorityBlockingQueue<Event> eventQueueOrdering = new PriorityBlockingQueue<>();

		final ServerSocket clientServerSocket = new ServerSocket(9099);
		clientServerSocket.setSoTimeout(1000);
		
		final ServerSocket eventServerSocket = new ServerSocket(9090);
		eventServerSocket.setSoTimeout(1000);
		
		final Server clientServer = new Server(clientServerSocket, Executors.newCachedThreadPool(), new ClientProcess(clients));
		final Server eventServer = new Server(eventServerSocket, Executors.newCachedThreadPool(), new EventProcess(eventQueue));
		final EventSortServer eventSortServer = new EventSortServer(eventQueue, eventQueueOrdering);
		final EventSendServer eventSendServer = new EventSendServer(clients, eventQueueOrdering);
		
		List<ServerAbs> servers = new LinkedList<>();
		servers.add(clientServer);
		servers.add(eventServer);
		servers.add(eventSortServer);
		servers.add(eventSendServer);
		
		new Application(Executors.newCachedThreadPool(), servers).start();
	}
	
}
