package com.java.tomcat.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * �̳߳ع��칤��
 *
 * @author MrWang
 * @date 2018��1��4��
 */
public class ExecutorServiceFactory {
	private static ExecutorServiceFactory executorFactory = new ExecutorServiceFactory();
	/**
	 * ��ʱ�����̳߳�
	 */
	private ExecutorService executors;

	private ExecutorServiceFactory() {
	}

	/**
	 * ��ȡExecutorServiceFactory
	 * 
	 * @return
	 */
	public static ExecutorServiceFactory getInstance() {
		return executorFactory;
	}

	/**
	 * ����һ���̳߳أ����ɰ����ڸ����ӳٺ�����������߶��ڵ�ִ�С�
	 * 
	 * @return
	 */
	public ExecutorService createScheduledThreadPool() {
		// CPU����
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		// ����
		executors = new ScheduledThreadPoolExecutor(availableProcessors * 10, getThreadFactory());
		return executors;
	}

	/**
	 * ����һ��ʹ�õ��� worker �̵߳� Executor�����޽���з�ʽ�����и��̡߳���ע�⣬�����Ϊ�ڹر�ǰ��ִ���ڼ����ʧ�ܶ���ֹ�˴˵����̣߳�
	 * ��ô�����Ҫ��һ�����߳̽�������ִ�к��������񣩡��ɱ�֤˳���ִ�и������񣬲��������������ʱ�䲻���ж���߳��ǻ�ġ���������Ч��
	 * newFixedThreadPool(1) ��ͬ���ɱ�֤�����������ô˷��������ص�ִ�г��򼴿�ʹ���������̡߳�
	 * 
	 * @return
	 */
	public ExecutorService createSingleThreadExecutor() {
		// ����
		executors = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
				getThreadFactory());
		return executors;
	}

	/**
	 * ����һ���ɸ�����Ҫ�������̵߳��̳߳أ���������ǰ������߳̿���ʱ���������ǡ�����ִ�кܶ�����첽����ĳ�����ԣ���Щ�̳߳�ͨ������߳������ܡ�����
	 * execute ��������ǰ������̣߳�����߳̿��ã�����������߳�û�п��õģ��򴴽�һ�����̲߳���ӵ����С���ֹ���ӻ������Ƴ���Щ���� 60
	 * ����δ��ʹ�õ��̡߳���ˣ���ʱ�䱣�ֿ��е��̳߳ز���ʹ���κ���Դ��ע�⣬����ʹ�� ThreadPoolExecutor
	 * ���췽�����������������Ե�ϸ�ڲ�ͬ�����糬ʱ���������̳߳ء�
	 * 
	 * @return
	 */
	public ExecutorService createCachedThreadPool() {
		// ����
		executors = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), getThreadFactory());
		return executors;
	}

	/**
	 * ����һ�������ù̶��߳������̳߳أ��Թ�����޽���з�ʽ��������Щ�̡߳�������㣬�ڴ���� nThreads
	 * �̻߳ᴦ�ڴ�������Ļ״̬������������̴߳��ڻ״̬ʱ�ύ��������
	 * �������п����߳�֮ǰ�����������ڶ����еȴ�������ڹر�ǰ��ִ���ڼ�����ʧ�ܶ������κ��߳���ֹ
	 * ����ôһ�����߳̽�������ִ�к��������������Ҫ������ĳ���̱߳���ʽ�عر�֮ǰ�����е��߳̽�һֱ���ڡ�
	 * @param threadMax 
	 * 
	 * @return
	 */
	public ExecutorService createFixedThreadPool(int threadMin, int threadMax) {
		// ����
		executors = new ThreadPoolExecutor(threadMin, threadMax, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(), getThreadFactory());
		return executors;
	}

	/**
	 * ��ȡ�̳߳ع���
	 * 
	 * @return
	 */
	private ThreadFactory getThreadFactory() {
		return new ThreadFactory() {
			AtomicInteger sn = new AtomicInteger();

			public Thread newThread(Runnable r) {
				SecurityManager s = System.getSecurityManager();
				ThreadGroup group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
				Thread t = new Thread(group, r);
				t.setName("�����߳� - " + sn.incrementAndGet());
				return t;
			}
		};
	}
}