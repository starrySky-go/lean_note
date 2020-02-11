package cn.itcast_01;

/*
 * 线程是依赖于进程而存在的。
 * 
 * 进程：进程就是正在运行的程序，是系统进行资源分配和调用的独立单位。每一个进程都有它自己的内存空间和系统资源。
 * 多进程有什么意义呢?
 * 		多进程的作用不是提高执行速度，而是提高CPU的使用率。
 * 		单核计算机：CPU多个进程间进行高效的切换，在任意一个时刻，只能有一个进程运行。
 * 
 * 线程：线程是程序中单个顺序的控制流，是程序使用CPU的基本单位。
 * 多线程有什么意义呢?
 * 		多线程的作用不是提高执行速度，而是为了提高应用程序的使用率。
 * 
 * 		因为多个线程共享同一个进程的资源(堆内存和方法区)，但是栈内存是独立的，一个线程一个栈。
 * 		所以他们仍然是在抢CPU的资源执行。一个时间点上只有能有一个线程执行。而且谁抢到，这个不一定，所以，造成了线程运行的随机性。
 * 
 * 并行和并发。
 *		前者是逻辑上同时发生，指在某一个时间内同时运行多个程序。
 *		后者是物理上同时发生，指在某一个时间点同时运行多个程序。
 *		那么，我们能不能实现真正意义上的并发呢，是可以的，多个CPU就可以实现，不过你得知道如何调度和控制它们。
 *
 * 多线程概述
 *		进程：
 * 			正在运行的程序，是系统进行资源分配和调用的独立单位。
 * 			每一个进程都有它自己的内存空间和系统资源。
 * 		线程：
 * 			是进程中的单个顺序控制流，是一条执行路径
 * 			一个进程如果只有一条执行路径，则称为单线程程序。
 *			一个进程如果有多条执行路径，则称为多线程程序。
 *
 *		举例：
 *			扫雷游戏(游戏，计时器),迅雷(可以同时下载多个文件)
 *
 * 接下来，我们就要来实现一个多线程的程序了。
 * 问题：请问如果是我们自己实现，该怎么办呢?
 * 		多线程程序，应该有线程，而线程是依赖于进程而存在，也就是说我要做一个线程，就必须首先来一个进程。
 * 		但是，问题来了，进程是操作系统来实现的，而Java语言是不能直接和操作系统打交道的，所以，我们通过java语言自己来实现多线程，是实现不了的。
 * 		这个时候，为了方便我们能够实现多线程程序，java就提高了一个类供我们使用：Thread
 * 			Thread -- Java
 * 			底层：C或者C++来调用了操作系统的一些资源，帮我们实现了进程的创建，按照java语言的规则提供java语言可以访问的内容就可以了。
 * 
 * 我们就进入Thread类的学习,通过查看API，我们知道创建新执行线程有两种方法。
 * 方式1：继承Thread类
 * 方式2：实现Runnable接口
 * 
 * 继承Thread类的步骤：
 * 		A:自定义类MyThread继承自Thread类
 * 		B:重写run()方法
 * 			因为run()方法封装的代码才是被线程执行的代码。
 * 		C:创建子类对象
 * 		D:启动线程
 * 			启动线程使用的是那个方法呢?
 * 			start():启动线程，并自动调用run()方法。
 * 
 * 几个常见的小问题：
 * 		A:为什么要重写run()方法?
 * 		B:启动线程使用的是那个方法?
 * 		C:start()和run()的区别?
 * 			start:启动线程，并自动调用run()方法
 * 			run:直接调用，仅仅是普通方法的调用
 * 		D:线程能不能多次启动?
 * 			不能
 */
public class MyThreadDemo {
	public static void main(String[] args) {
		// 创建子类对象
		// MyThread mt = new MyThread();
		// mt.run();
		// mt.run();

		// MyThread mt1 = new MyThread();
		// MyThread mt2 = new MyThread();
		// mt1.run();
		// mt2.run();

		MyThread mt1 = new MyThread();
		MyThread mt2 = new MyThread();
		mt1.start();
		mt2.start();

		// IllegalThreadStateException
		// mt1.start();
	}
}
