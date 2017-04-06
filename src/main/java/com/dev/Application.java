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
import com.dev.user.User;

public class Application {
//	private static final Map<Integer, Socket> clients = new ConcurrentHashMap<>();
	private static final Map<Integer, User> clients = new ConcurrentHashMap<>();
	private final ExecutorService executorService;
	private final List<Runnable> servers;
	
	public Application(ExecutorService executorService, List<Runnable> servers) {
		this.executorService = executorService;
		this.servers = servers;
	}
	
	public void start() {
		for (Runnable server : servers) {
			executorService.execute(server);
		}
		while (true) {
			executorService.shutdownNow();
		}
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocket clientServerSocket = new ServerSocket(9099);
		System.out.println("client server open port number 9099...");
		
		ServerSocket eventServerSocket = new ServerSocket(9090);
		System.out.println("event server open port number 9090...");
		
		PriorityBlockingQueue<Event> eventQueue = new PriorityBlockingQueue<>();
		
		Server clientServer = new Server(clientServerSocket, new ClientProcess(clients), Executors.newCachedThreadPool());
		Server eventServer = new Server(eventServerSocket, new EventProcess(eventQueue), Executors.newCachedThreadPool());
		ProcessServer processServer = new ProcessServer(clients, eventQueue);
		
		List<Runnable> servers = new LinkedList<>();
		servers.add(clientServer);
		servers.add(eventServer);
		servers.add(eventServer);
		servers.add(processServer);
		new Application(Executors.newCachedThreadPool(), servers).start();
	}
	
}
