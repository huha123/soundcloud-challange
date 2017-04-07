package com.dev;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.dev.event.EventType;
import com.dev.user.User;

public class ApplicationTests {

	@Test
	public void test1() throws InterruptedException {
		AtomicInteger atomicInteger = new AtomicInteger(1);
		PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();
		
		queue.put(new User(3, null));
		queue.put(new User(7, null));
		queue.put(new User(4, null));
		queue.put(new User(2, null));
		queue.put(new User(5, null));
		queue.put(new User(11, null));
		queue.put(new User(12, null));
		queue.put(new User(6, null));
		queue.put(new User(9, null));
		queue.put(new User(8, null));
		queue.put(new User(13, null));
		queue.put(new User(1, null));
		queue.put(new User(10, null));

		while(queue.size() > 0) {
			if (queue.peek().getUserId() <= atomicInteger.get()) {
				System.out.println(queue.poll().getUserId() + ":" + atomicInteger.getAndIncrement());
			}
		}
	}
	
	@Test
	public void test2() {
		String type = "F";
		System.out.println(EventType.valueOf(type).equals(EventType.F));
		System.out.println(EventType.valueOf(type).equals(EventType.B));
	}
}
