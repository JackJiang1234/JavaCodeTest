package concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleJavaApp {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("this_is_main");
        //Thread.dumpStack();

        Lock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(Thread.holdsLock(lock));
        } finally {
            lock.unlock();
        }

        System.out.println(Thread.holdsLock(lock));


        /*
        while (true){
            System.out.println(new Date());
            Thread.sleep(1000);
        }*/
        /*
        * 1.做一个demo
            分级发短信（demo就打印消息 ），比如 公司领导，部门主管，小组组长，组成员4级，
            假如有个 疫情预警上报（demo 调用web api），先通知组人员（多个人），如果有一个处理就算处理了，如果3分钟（demo 可以时间缩短）没处理，
            就往组长发短信，如果组长3分钟没处理就往组长的上级推送。如果没处理，就按这样一级一级的往上推送消息(打印消息)。
            要求单程序并发至少支持1000tps 预警事件 
        *
        * */

        /**
         *
         分级发短信（demo就打印消息 ），比如 公司领导，部门主管，小组组长，组成员4级，
         假设让你来开发个疫情预警上报程序，先通知组人员（多个人），如果有一个处理就算处理了，如果3分钟（demo 可以时间缩短）没处理，
         就往组长发短信，如果组长3分钟没处理就往组长的上级推送。如果没处理，就按这样一级一级的往上推送消息(打印消息)。
         *
         * */
    }
}
