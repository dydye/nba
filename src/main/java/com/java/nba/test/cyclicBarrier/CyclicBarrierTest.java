package com.java.nba.test.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author daiyun
 * @date 2019-4-14
 */
public class CyclicBarrierTest {

	private static class Worker extends Thread {

		private CyclicBarrier cyclicBarrier;

		public Worker(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			super.run();
			try {
				String fdName = Thread.currentThread().getName();
				System.out.println(fdName + "开始等待其他线程");
				System.out.println("阻塞的线程数：" + cyclicBarrier.getNumberWaiting());
				System.out.println(fdName + ":" + cyclicBarrier.isBroken());
				cyclicBarrier.await();

				System.out.println("await结束，" + fdName + "开始执行");
				//模拟业务处理
				Thread.sleep(1000);
				System.out.println(fdName + "执行完毕");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		int threadCount = 3;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

		for (int i = 0; i < threadCount; i++){
			System.out.println("创建线程：" + i);
			Worker worker = new Worker(cyclicBarrier);
			worker.start();
		}


	}

}
