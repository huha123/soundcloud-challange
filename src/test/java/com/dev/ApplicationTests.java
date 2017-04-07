package com.dev;

import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

import com.dev.event.EventType;
import com.dev.user.User;

public class ApplicationTests {

	@Test
	public void test1() throws InterruptedException {
		PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();
		
		queue.put(new User(2, null));
		queue.put(new User(4, null));
		queue.put(new User(14, null));
		queue.put(new User(15, null));
		queue.put(new User(12, null));
		queue.put(new User(10, null));
		queue.put(new User(18, null));
		queue.put(new User(17, null));
		queue.put(new User(16, null));
		queue.put(new User(5, null));
		queue.put(new User(3, null));
		queue.put(new User(1, null));
		queue.put(new User(0, null));

		while(queue.size() > 0) {
			System.out.println(queue.poll().getUserId());
		}
	}
	
	@Test
	public void test2() {
		String type = "F";
		System.out.println(EventType.valueOf(type).equals(EventType.F));
		System.out.println(EventType.valueOf(type).equals(EventType.B));
		
	}
	
}
