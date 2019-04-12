package com.java.nba.test.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author daiyun
 * @date 2019-4-12
 */
public class NetworkHealthCheck extends BaseHealthCheck {

	public NetworkHealthCheck(CountDownLatch countDownLatch) {
		super(countDownLatch, NetworkHealthCheck.class.getName());
	}

	@Override
	public void verifyService() {
		logger.info("进行{}检测中,线程：{}", NetworkHealthCheck.class.getName(), Thread.currentThread().getName());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
