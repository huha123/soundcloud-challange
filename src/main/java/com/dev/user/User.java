package com.dev.user;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class User implements Comparable<User> {
	private final Integer userId;
	private PrintWriter writer;
	private final Set<User> followers;
	private List<String> notConnectUser = new LinkedList<>();
	
	public User(Integer userId, PrintWriter writer, Set<User> followers) {
		this.userId = userId;
		this.writer = writer;
		this.followers = followers;
	}
	
	public User(Integer userId, Set<User> followers, List<String> notConnectUser) {
		this.userId = userId;
		this.followers = followers;
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
