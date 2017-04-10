package com.dev.user;

import java.io.PrintWriter;
import java.util.Map;
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
	
	public void notifyAllUser(String message) {
		for (User user : followers) {
			user.notifyUser(message);
		}
	}
	
	public void addFollower(User fromUser) {
		followers.add(fromUser);
	}
	
	public void removeFollower(User fromUser) {
		followers.remove(fromUser);
	}
	
	@Override
	public int compareTo(User o) {
		return userId.compareTo(o.userId);
	}
}
