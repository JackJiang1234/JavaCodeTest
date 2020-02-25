package concurrency.base.init;

import concurrency.base.util.Debug;
import concurrency.base.util.Tools;

import java.util.HashSet;

public class ClassLazyInitDemo {
    public static void main(String[] args) throws InterruptedException {
        HashSet<Thread> threads = new HashSet<>();
        for (int i = 0; i < 30; i++){
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    showInfo();
                }
            }));
        }
        Tools.startAndWaitTerminated(threads);
    }

    static void showInfo(){
        Debug.info(Collaborator.class.hashCode());
        Debug.info(Collaborator.number);
        Debug.info(Collaborator.flag);
    }

    static class Collaborator {
        static int number = 1;
        static boolean flag = true;

        static {
            Debug.info("Collaborator initiazlizing...");
        }
    }
}
