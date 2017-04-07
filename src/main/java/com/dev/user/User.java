package com.dev.user;

import java.io.PrintWriter;
import java.util.Set;

public class User implements Comparable<User> {
	private Integer userId;
	private PrintWriter writer;
	private Set<User> followers;
	
	public User(Integer userId, PrintWriter writer) {
		this.userId = userId;
		this.writer = writer;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public boolean notifyUser(String message) {
		if (writer != null) {
			writer.println(message);
			return writer.checkError();
			
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(User o) {
		return userId.compareTo(o.userId);
	}

}
