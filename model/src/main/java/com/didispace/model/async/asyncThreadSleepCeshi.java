package com.didispace.model.async;


class asyncCeshiThread extends Thread {

	 private Thread t;
	   private String threadName;
	   
	   asyncCeshiThread( String name) {
	      threadName = name;
	      System.out.println("Creating " +  threadName );
	   }
	   
	   public void run() {
	      System.out.println("Running " +  threadName );
	      try {
	         for(int i = 4; i > 0; i--) {
	            System.out.println("Thread: " + threadName + ", " + i);
	            // 让线程睡眠一会
	            Thread.sleep(3000L);
	         }
	      }catch (InterruptedException e) {
	         System.out.println("Thread " +  threadName + " interrupted.");
	      }
	      System.out.println("Thread " +  threadName + " exiting.");
	   }
	   
	   public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	}
	 
public class asyncThreadSleepCeshi {
	 
	   public static void main(String args[]) {
		   
		  asyncCeshiThread T3 = new asyncCeshiThread("Thread-3");
		  T3.run();
		   
		  asyncCeshiThread T1 = new asyncCeshiThread( "Thread-1");
	      T1.start();
	      
	      
	      asyncCeshiThread T2 = new asyncCeshiThread( "Thread-2");
	      T2.run();
	   }   
	}