package com.java.nba.test.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author daiyun
 * @date 2019-4-12
 */
public class DataHealthCheck extends BaseHealthCheck {

	public DataHealthCheck(CountDownLatch countDownLatch) {
		super(countDownLatch, DataHealthCheck.class.getName());
	}

	@Override
	public void verifyService() {
		logger.info("进行{}检测中, 线程：{}", DataHealthCheck.class.getName(), Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
