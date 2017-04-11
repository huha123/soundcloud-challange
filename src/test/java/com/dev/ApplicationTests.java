package com.dev;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

import com.dev.event.EventType;
import com.dev.user.User;

public class ApplicationTests {

	@Test
	public void test1() throws InterruptedException {
		AtomicInteger atomicInteger = new AtomicInteger(1);
		PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();
		
		queue.put(new User(3, null, null, true));
		queue.put(new User(7, null, null, true));
		queue.put(new User(4, null, null, true));
		queue.put(new User(2, null, null, true));
		queue.put(new User(5, null, null, true));
		queue.put(new User(11, null, null, true));
		queue.put(new User(12, null, null, true));
		queue.put(new User(6, null, null , true));
		queue.put(new User(9, null, null, true));
		queue.put(new User(8, null, null, true));
		queue.put(new User(13, null, null, true));
		queue.put(new User(1, null, null, true));
		queue.put(new User(10, null, null, true));

		while(queue.size() > 0) {
			if (queue.peek().getUserId() <= atomicInteger.get()) {
				System.out.println(queue.poll().getUserId() + ":" + atomicInteger.getAndIncrement());
			}
		}
	}
	
	@Test
	public void test2() {
		String type = "F";
		Assert.assertEquals(EventType.valueOf(type), EventType.F);
		Assert.assertEquals(EventType.valueOf(type), EventType.B);
	}
	
	@Test
	public void test3() {
		Set<Integer> map = new ConcurrentSkipListSet<>();
		map.add(1);
		map.add(1);
		map.add(1);
		map.add(2);
		
		for (Integer integer : map) {
			System.out.println("integer:" + integer + ",  map Size:" + map.size());
		}
	}
	
	@Test
	public void test4() {
		final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
		System.out.println("init atomicBoolean :" + atomicBoolean.get());
		
		atomicBoolean.set(true);
		System.out.println("after atomicBoolean :" + atomicBoolean);
	}
}
