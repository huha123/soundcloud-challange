package com.dev;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.dev.event.EventType;
import com.dev.user.User;

public class ApplicationTests {

	@Test
	public void test1() throws InterruptedException {
		AtomicInteger atomicInteger = new AtomicInteger(1);
		PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();
		
		queue.put(new User(3, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(7, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(4, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(2, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(5, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(11, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(12, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(6, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(9, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(8, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(13, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(1, null, new ConcurrentSkipListSet<>()));
		queue.put(new User(10, null, new ConcurrentSkipListSet<>()));

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
}
