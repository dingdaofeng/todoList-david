package com.superloop.interview.todo.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * used to generate increased id for todo item
 * 
 * @author David Ding
 *
 */
public class IdFactory {
	private static IdFactory instance = new IdFactory();

	private AtomicLong currentId = new AtomicLong();

	private IdFactory() {

	}

	public static IdFactory getInstance() {
		return instance;
	}

	public long next() {
		return currentId.incrementAndGet();
	}

}
