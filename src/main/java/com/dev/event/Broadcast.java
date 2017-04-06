package com.dev.event;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import com.dev.user.User;

public class Broadcast extends Event {
	private final int seq;
	private final String[] splitMessage;
	private final String message;
	private PrintWriter writer;
	
	public Broadcast(int seq, String[] splitMessage, String message) {
		super(seq);
		this.seq = seq;
		this.splitMessage = splitMessage;
		this.message = message;
	}

	@Override
	public void sendMessageUser(Map<Integer, User> clients) {
		System.out.println("Broadcast [" + seq + "][" + splitMessage[1] + "] :" + message);
		Socket socket;
		for (User user : clients.values()) {
			try {
				socket = user.getSocket();
				writer = new PrintWriter(socket.getOutputStream());
				writer.println(message);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
