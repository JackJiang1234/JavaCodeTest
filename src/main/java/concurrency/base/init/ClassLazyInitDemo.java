package concurrency.base.init;

import concurrency.base.util.Debug;

public class ClassLazyInitDemo {
    public static void main(String[] args) {
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