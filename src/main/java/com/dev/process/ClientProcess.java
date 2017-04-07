package com.dev.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.dev.user.User;

public class ClientProcess implements Process {
	private Map<Integer, User> clients;
	private AtomicInteger atomicInteger = new AtomicInteger(0);
	
	public ClientProcess(Map<Integer, User> clients) {
		this.clients = clients;
	}
	
	@Override
	public void process(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		String message;
		while ((message = reader.readLine()) != null) {
			int userId = Integer.parseInt(message);
			
			if(clients.containsKey(userId) == false) {
				clients.put(userId, new User(userId, writer));
				System.out.println("client message["+atomicInteger.incrementAndGet()+"]:" + message);
			}
		}
	}
}
