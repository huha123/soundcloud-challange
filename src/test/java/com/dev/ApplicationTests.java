package com.dev;

import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

import com.dev.user.User;

public class ApplicationTests {

	@Test
	public void test1() throws InterruptedException {
		PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();
		
		queue.put(new User(2, null));
		queue.put(new User(4, null));
		queue.put(new User(1, null));
		queue.put(new User(5, null));
		queue.put(new User(3, null));

		while(queue.size() > 0) {
//			System.out.println(queue.take().getUserId());
//			System.out.println(queue.peek().getUserId());
			System.out.println(queue.poll().getUserId());
		}
		
	}
	
}
