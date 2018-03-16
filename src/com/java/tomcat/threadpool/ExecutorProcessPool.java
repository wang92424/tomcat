package com.java.tomcat.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * �̴߳�����
 *
 * @author MrWang
 * @date   2018��1��4��
 */
public class ExecutorProcessPool {

    private final int threadMin = 2;
    private final int threadMax = 20;
    private ExecutorService executor;
    private static ExecutorProcessPool pool = new ExecutorProcessPool();

    private ExecutorProcessPool() {
        System.out.println("threadMax>>>>>>>" + threadMax);
        executor = ExecutorServiceFactory.getInstance().createFixedThreadPool(threadMin,threadMax);
    }

    public static ExecutorProcessPool getInstance() {
        return pool;
    }

    /**
     * �ر��̳߳أ�����Ҫ˵�����ǣ����ùر��̳߳ط������̳߳ػ�ִ��������е�����������˳�
     * 
     * @author SHANHY
     * @date   2015��12��4��
     */
    public void shutdown(){
        executor.shutdown();
    }

    /**
     * �ύ�����̳߳أ����Խ����̷߳���ֵ
     * 
     * @param task
     * @return
     * @author SHANHY
     * @date   2015��12��4��
     */
    public Future<?> submit(Runnable task) {
        return executor.submit(task);
    }

    /**
     * �ύ�����̳߳أ����Խ����̷߳���ֵ
     * 
     * @param task
     * @return
     * @author SHANHY
     * @date   2015��12��4��
     */
    public Future<?> submit(Callable<?> task) {
        return executor.submit(task);
    }

    /**
     * ֱ���ύ�����̳߳أ��޷���ֵ
     * 
     * @param task
     * @author SHANHY
     * @date   2015��12��4��
     */
    public void execute(Runnable task){
        executor.execute(task);
    }

}