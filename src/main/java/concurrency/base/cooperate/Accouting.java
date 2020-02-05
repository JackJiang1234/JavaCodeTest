package concurrency.base.cooperate;

public class Accouting {
    void accouting() {
        /*
        while(存在未对账订单){
            // 查询未对账订单
            pos = getPOrders();
            // 查询派送单
            dos = getDOrders();
            // 执行对账操作
            diff = check(pos, dos);
            // 差异写入差异库
            save(diff);
        }*/
    }

    void useThreadJoin() {
        /*
        while(存在未对账订单){
            // 查询未对账订单
            Thread T1 = new Thread(()->{
                pos = getPOrders();
            });
            T1.start();
            // 查询派送单
            Thread T2 = new Thread(()->{
                dos = getDOrders();
            });
            T2.start();
            // 等待T1、T2结束
            T1.join();
            T2.join();
            // 执行对账操作
            diff = check(pos, dos);
            // 差异写入差异库
            save(diff);
        }*/
    }

    void useCountDownLatch() {
        /*
        // 创建2个线程的线程池
        Executor executor =
                Executors.newFixedThreadPool(2);
        while(存在未对账订单){
            // 计数器初始化为2
            CountDownLatch latch =
                    new CountDownLatch(2);
            // 查询未对账订单
            executor.execute(()-> {
                pos = getPOrders();
                latch.countDown();
            });
            // 查询派送单
            executor.execute(()-> {
                dos = getDOrders();
                latch.countDown();
            });

            // 等待两个查询操作结束
            latch.await();

            // 执行对账操作
            diff = check(pos, dos);
            // 差异写入差异库
            save(diff);
        }*/
    }

    void useCyclicBarrier() {
        /*
        // 订单队列
        Vector<P> pos;
        // 派送单队列
        Vector<D> dos;
        // 执行回调的线程池
        Executor executor =
          Executors.newFixedThreadPool(1);
        final CyclicBarrier barrier =
          new CyclicBarrier(2, ()->{
            executor.execute(()->check());
          });

        void check(){
          P p = pos.remove(0);
          D d = dos.remove(0);
          // 执行对账操作
          diff = check(p, d);
          // 差异写入差异库
          save(diff);
        }

        void checkAll(){
          // 循环查询订单库
          Thread T1 = new Thread(()->{
            while(存在未对账订单){
              // 查询订单库
              pos.add(getPOrders());
              // 等待
              barrier.await();
            }
          });
          T1.start();
          // 循环查询运单库
          Thread T2 = new Thread(()->{
            while(存在未对账订单){
              // 查询运单库
              dos.add(getDOrders());
              // 等待
              barrier.await();
            }
          });
          T2.start();
        }*/
    }

}
