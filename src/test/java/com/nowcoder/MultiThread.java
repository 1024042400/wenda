package com.nowcoder;
class MyThread extends Thread {
    private int tid;
    public MyThread(int tid) {
        this.tid = tid;
    }
    @Override
    public void run(){
        try{
            for(int i=0;i<10;i++) {
                Thread.sleep(1000);
                System.out.println(String.format("%d:%d",tid,i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

public class MultiThread {
    public static void testThread() {
        for(int i=0;i<10;i++){
            new MyThread(i).start();
        }
    }
    private static Object object = new Object();

    public static void testSynchronized1(){
        synchronized(object) {
            try{
                for(int i=0;i<10;i++) {
                    Thread.sleep(1000);
                    System.out.println(String.format("T3 :%d",i));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void testSynchronized2(){
        synchronized(object) {
            try{
                for(int i=0;i<10;i++) {
                    Thread.sleep(1000);
                    System.out.println(String.format("T4 :%d",i));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void testSynchronized() {
        for(int i=0;i<10;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testSynchronized1();
                    testSynchronized2();
                }
            }).start();
        }
    }

    public static void main(String args[]){
//        testThread();
        testSynchronized();
    }
}
