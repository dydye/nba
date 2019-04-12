package com.java.nba.test.countDownLatch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * 线程检测基类，需要检测相关业务的可以继承此基类
 * @author daiyun
 * @date 2019-4-12
 */
public abstract class BaseHealthCheck implements Runnable {

	protected static final Logger logger = LoggerFactory.getLogger(BaseHealthCheck.class);
	private CountDownLatch countDownLatch;
	private String serviceName;
	private boolean serviceUp;

	public BaseHealthCheck(CountDownLatch countDownLatch, String serviceName) {
		this.countDownLatch = countDownLatch;
		this.serviceName = serviceName;
		this.serviceUp = false;
	}

	@Override
	public void run() {

		try {
			verifyService();
			serviceUp = true;
		} catch (Exception e){
			logger.error("检测异常", e);
		} finally {
			if (countDownLatch != null){
				countDownLatch.countDown();
			}
		}


	}

	/**
	 * 业务名称
	 * @param serviceName
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * 业务是否启动完毕
	 * @param serviceUp
	 */
	public void isServiceUp(boolean serviceUp) {
		this.serviceUp = serviceUp;
	}

	/**
	 * 供给其他线程启动时重载，加载相应的业务
	 */
	public abstract void verifyService();
}
