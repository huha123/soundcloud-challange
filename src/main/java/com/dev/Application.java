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
import com.dev.server.ProcessServer;
import com.dev.server.Server;
import com.dev.server.ServerAbs;
import com.dev.user.User;

public class Application {
	private static final Map<Integer, User> clients = new ConcurrentHashMap<>();
	private final ExecutorService executorService;
	private final List<ServerAbs> servers;
	
	public Application(ExecutorService executorService, List<ServerAbs> servers) {
		this.executorService = executorService;
		this.servers = servers;
	}
	
	public void start() {
		for (Runnable server : servers) {
			executorService.execute(server);
		}
	}
	
	public static void main(String[] args) throws IOException {
		final ServerSocket clientServerSocket = new ServerSocket(9099);
		clientServerSocket.setSoTimeout(1000);
		System.out.println("client server open port number 9099...");
		
		final ServerSocket eventServerSocket = new ServerSocket(9090);
		eventServerSocket.setSoTimeout(1000);
		System.out.println("event server open port number 9090...");
		
		PriorityBlockingQueue<Event> eventQueue = new PriorityBlockingQueue<>();
		
		final Server clientServer = new Server(clientServerSocket, new ClientProcess(clients), Executors.newCachedThreadPool());
		final Server eventServer = new Server(eventServerSocket, new EventProcess(eventQueue), Executors.newCachedThreadPool());
		final ProcessServer processServer = new ProcessServer(clients, eventQueue);
		
		List<ServerAbs> servers = new LinkedList<>();
		servers.add(clientServer);
		servers.add(eventServer);
		servers.add(processServer);
		new Application(Executors.newCachedThreadPool(), servers).start();
	}
	
}
