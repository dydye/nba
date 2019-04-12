package com.java.nba.test;

import com.java.nba.test.countDownLatch.BaseHealthCheck;
import com.java.nba.test.countDownLatch.DataHealthCheck;
import com.java.nba.test.countDownLatch.NetworkHealthCheck;
import com.java.nba.test.thread.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author daiyun
 * @date 2019-4-12
 */
public class Test1 {

	private static final Logger logger = LoggerFactory.getLogger(Test1.class);
	private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 1L,
			TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), new NamedThreadFactory("Test1"));

	public static void main(String[] args) {

		CountDownLatch countDownLatch = new CountDownLatch(2);
		List<BaseHealthCheck> runnableList = Arrays.asList
				(new BaseHealthCheck[]{new DataHealthCheck(countDownLatch),new NetworkHealthCheck(countDownLatch)});
		for (BaseHealthCheck baseHealthCheck : runnableList){
			executor.execute(baseHealthCheck);
		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("所有业务加载完毕");
		executor.shutdown();
	}
}
