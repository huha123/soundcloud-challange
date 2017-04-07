package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public abstract class Event implements Comparable<Event> {
	private final int seq;
	
	public Event(int seq) {
		this.seq = seq;
	}
	
	public int getSeq() {
		return seq;
	}
	
	public abstract void notifyUser(Map<Integer, User> clients);
	
	@Override
	public int compareTo(Event o) {
		return Integer.compare(seq, o.seq);
	}
}
