package com.dev.event;

import java.util.Map;

import com.dev.user.User;

public class StatusUpdate extends Event{
	private final int seq;
	private final String[] splitMessage;
	private final String message;
	
	public StatusUpdate(int seq, String[] splitMessage, String message) {
		super(seq);
		this.seq = seq;
		this.splitMessage = splitMessage;
		this.message = message;
	}

	@Override
	public void notifyUser(Map<Integer, User> clients) {
		System.out.println("StatusUpdate [" + seq + "][" + splitMessage[1] + "] :" + message);
	}

}
