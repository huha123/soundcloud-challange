package com.dev.user;

import java.net.Socket;

public class User implements Comparable<User> {
	private Socket socket;
	private Integer userId;
	
	public User(Integer userId, Socket socket) {
		this.userId = userId;
		this.socket = socket;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public Socket getSocket() {
		return socket;
	}

	@Override
	public int compareTo(User o) {
		return userId.compareTo(o.userId);
	}

}
