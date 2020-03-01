package concurrency.base.cooperate.demo.service;

public interface Service {
    void start();
    void stop();
    boolean isStarted();
}
