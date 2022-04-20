package com.algorithm.demo;

import com.algorithm.demo.algorithms.SearchAlgorithm;
import com.algorithm.demo.structures.MatrixStructures;
import com.algorithm.demo.thread.*;
import com.algorithm.demo.thread.waitandnotify.Data;
import com.algorithm.demo.thread.waitandnotify.Receiver;
import com.algorithm.demo.thread.waitandnotify.Sender;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void findV()
	{
		int index = SearchAlgorithm.binary_search(new int[]{1,6,13,22,36,65,98,106,222,333}, 106);
		System.out.println("查找结果：" + index);
	}

	@Test
	void create_matrix()
	{
		int [][] aa = { {5, 4, 7}, {1, 3, 8}, {2, 9, 6} };
		int[][] matrix = MatrixStructures.create_matrix();
	}

	class MyThread extends Thread {
		private int i = 5;
		public void run() {
			System.out.println("i=" + (i--) + " threadName=" + Thread.currentThread().getName());
		}
	}

	@Test
	public void thread_run1() {
		// 创建一个MyThread对象，并将该对象分别加载到五个线程中并分别给线程命名
		MyThread myThread = new MyThread();
		Thread t1 = new Thread(myThread);
		Thread t2 = new Thread(myThread);
		Thread t3 = new Thread(myThread);
		Thread t4 = new Thread(myThread);
		Thread t5 = new Thread(myThread);

		//System.out.println("Caught:" + t1.getState());

		// 启动五个线程
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

	@Test
	void thread_can(ExtendsThreadDemo demo)
	{
		demo.k1 = 20;
	}

	/**
	 * 使用AtomicBoolean停止线程
	 * 测试1、sleep很短，表示不阻塞线程
	 * 测试2、sleep时间很长，表示阻塞线程
	 */
	@Test
	void thread_stop_with_bool()
	{
		// creating two objects t1 & t2 of MyThread
		MyThreadDemo t1 = new MyThreadDemo("First  thread");
		MyThreadDemo t2 = new MyThreadDemo("Second thread");
		try {
			Thread.sleep(5000);
			t1.stop(); // stopping thread t1
			t2.stop(); // stopping thread t2
		}
		catch (InterruptedException e) {
			System.out.println("Caught:" + e);
		}
		System.out.println("Exiting the main Thread");

		try {
			Thread.sleep(115000);
		}
		catch (InterruptedException e) {
			System.out.println("Caught:" + e);
		}
	}

	/**
	 * 使用interrupt中断线程
	 */
	@Test
	void thread_stop_with_bool_and_interrupt()
	{
		// creating two objects t1 & t2 of MyThread
		ControlSubThread t1 = new ControlSubThread();
		ControlSubThread t2 = new ControlSubThread();
		t1.start();
		t2.start();
		try {
			Thread.sleep(500);
			t1.interrupt(); // stopping thread t1
			t2.interrupt(); // stopping thread t2
			Thread.sleep(500);
		}
		catch (InterruptedException e) {
			System.out.println("Caught:" + e);
		}
		System.out.println("Exiting the main Thread");
	}

	boolean exit = true;

	@Test
	void thread_stop_with_bool_1()
	{
		System.out.println("started main thread..");

		new Thread() {
			public void run()
			{
				System.out.println("started inside thread..");

				// inside thread caches the value of exit,
				// so changes made to exit are not visible here
				while (exit) // will run infinitely
				{
					System.out.println("进入循环...");
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// this will not be printed.
				System.out.println("exiting inside thread..");
			}
		}.start();

		try {
			Thread.sleep(500);
		}
		catch (InterruptedException e) {
			System.out.println("Caught :" + e);
		}

		// so that we can stop the threads
		exit = false;
		System.out.println("exiting main thread..");
	}

	@Test
	void thread_run()
	{
		ExtendsThreadDemo demo = new ExtendsThreadDemo();
		thread_can(demo);
		List<Thread> list = new ArrayList<>();
		for(int i=0; i<80; i++)
		{
			list.add(new Thread(demo));
		}

		//for(Thread a : list)

		list.forEach((e) -> {
			e.start();
		});

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


//		Demo1 demo1 = new Demo1();
//		demo1.start();

//		ImplementsCallableDemo demo2 = new ImplementsCallableDemo();
//		demo2.RunTask();
//		System.out.println("结束了");
//		demo2.GetResult();
	}

	@Test
	void test_deadlock()
	{
		DeadLock2.main_1();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("结束");
	}

	@Test
	void test_join()
	{
		Thread t2 = new SampleThread(1);
		t2.start();
		System.out.println("Invoking join");
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Returned from join");
		System.out.println(t2.isAlive());
	}

	@Test
	void test_waitandnofity()
	{
		Data data = new Data();
		Thread sender = new Thread(new Sender(data));
		Thread receiver = new Thread(new Receiver(data));
		sender.start();
		receiver.start();

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Test
	void test_waitandnofity_123()
	{
		 String temp123 = "uiop";
		final ThreadLocal threadLocal = new InheritableThreadLocal();
		// 主线程
		threadLocal.set("不擅技术");

		new Thread()
		{
			@Override
			public void run()
			{
				System.out.println(temp123 + "|" + threadLocal.get());
			}
		}.start();
	}
}
