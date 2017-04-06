package com.dev.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

import com.dev.user.User;

public class ClientProcess implements Process {
	private Map<Integer, User> clients;
	
	public ClientProcess(Map<Integer, User> clients) {
		this.clients = clients;
	}
	
	@Override
	public void process(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
		String message;
		while ((message = reader.readLine()) != null) {
			System.out.println("client message:" + message);
			int userId = Integer.parseInt(message);
			if (clients.containsKey(userId) == false) {
				clients.put(userId, new User(userId, socket));
			}
		}
	}
}
