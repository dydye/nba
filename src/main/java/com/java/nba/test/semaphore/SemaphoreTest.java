package com.java.nba.test.semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量，假设有十个人在银行办理业务，但是只有两个窗口，因此每次最多2个人同事办理业务，其他人需要等待
 * @author daiyun
 * @date 2019-4-15
 */
public class SemaphoreTest {


	private static final int clientTotal = 11;

	private static final int threadTotal = 2;

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executorService = Executors.newCachedThreadPool();

		final Semaphore semaphore = new Semaphore(threadTotal);
		CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++){
			final int count = i;
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "办理业务中:" + count);
					Thread.sleep(2000);
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await();
		System.out.println("业务遍历结束");
		executorService.shutdown();

	}

}
