package com.dev.user;

import java.io.PrintWriter;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class User implements Comparable<User> {
	private final Integer userId;
	private PrintWriter writer;
	private final Set<User> followers;
	private final AtomicBoolean isConnect = new AtomicBoolean(false);
	
	public User(Integer userId, PrintWriter writer, Set<User> followers, boolean isConnect) {
		this.userId = userId;
		this.writer = writer;
		this.followers = followers;
		this.isConnect.set(isConnect);
	}
	
	public int getUserId() {
		return userId;
	}
	
	public boolean notifyUser(String message) {
		if (writer != null && isConnect.get()) {
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
