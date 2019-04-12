package com.java.nba.test.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可命名的线程工厂，可以自定义线程池工程的名称
 * @author daiyun
 * @date 2019-4-12
 */
public class NamedThreadFactory implements ThreadFactory {

	private static final AtomicInteger poolNum = new AtomicInteger(1);
	private final AtomicInteger threadNum = new AtomicInteger(1);
	private final String namePrefix;

	public NamedThreadFactory(String name) {
		this.namePrefix = name + "-pool-" +poolNum.getAndIncrement() + "-thread-";
	}

	@Override
	public Thread newThread(Runnable runnable) {
		return new Thread(runnable, namePrefix + threadNum.getAndIncrement());
	}
}
