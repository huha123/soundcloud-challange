package com.dev;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Ignore;
import org.junit.Test;

import com.dev.event.EventType;
import com.dev.user.User;

public class ApplicationTests {

	@Test
	public void test1() throws InterruptedException {
		AtomicInteger atomicInteger = new AtomicInteger(1);
		PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();
		
		queue.put(new User(3, null, null));
		queue.put(new User(7, null, null));
		queue.put(new User(4, null, null));
		queue.put(new User(2, null, null));
		queue.put(new User(5, null, null));
		queue.put(new User(11, null, null));
		queue.put(new User(12, null, null));
		queue.put(new User(6, null, null));
		queue.put(new User(9, null, null));
		queue.put(new User(8, null, null));
		queue.put(new User(13, null, null));
		queue.put(new User(1, null, null));
		queue.put(new User(10, null, null));

		while(queue.size() > 0) {
			if (queue.peek().getUserId() <= atomicInteger.get()) {
				System.out.println(queue.poll().getUserId() + ":" + atomicInteger.getAndIncrement());
			}
		}
	}
	
	@Ignore
	@Test
	public void test2() {
		String type = "F";
		System.out.println(EventType.valueOf(type).equals(EventType.F));
		System.out.println(EventType.valueOf(type).equals(EventType.B));
	}
	
	@Test
	public void test3() {
		Set<Integer> map = new ConcurrentSkipListSet<>();
		map.add(1);
		map.add(1);
		map.add(1);
		map.add(2);
		map.add(null);
		
		for (Integer integer : map) {
			System.out.println("integer:" + integer + ",  map Size:" + map.size());
		}
		
	}
}
