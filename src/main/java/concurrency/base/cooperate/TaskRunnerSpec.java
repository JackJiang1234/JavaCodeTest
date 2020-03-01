package concurrency.base.cooperate;

public interface TaskRunnerSpec {
    public void init();

    public void submit(Runnable task) throws InterruptedException;
}
