package concurrency.base.lock.loadbalancer;

import concurrency.base.util.Tools;

import java.util.HashSet;
import java.util.Set;

public class SystemBooter {

    public static void main(String[] args) throws Exception {
        SystemBooter sysBooter = new SystemBooter();
        ServiceInvoker rd = ServiceInvoker.getInstance();

        LoadBalancer lb = sysBooter.createLoadBalancer();

        // 在main线程中设置负载均衡器实例
        rd.setLoadBalancer(lb);


        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            new RequestSender().start();
        }
    }

    // 根据系统配置创建负载均衡器实例
    private LoadBalancer createLoadBalancer() throws Exception {
        LoadBalancer lb;
        Candidate candidate = new Candidate(loadEndpoints());
        lb = WeightedRoundRobinLoadBalancer.newInstance(candidate);
        return lb;
    }

    private Set<Endpoint> loadEndpoints() {
        Set<Endpoint> endpoints = new HashSet<Endpoint>();

        // 模拟从数据库加载以下信息
        endpoints.add(new Endpoint("192.168.101.100", 8080, 3));
        endpoints.add(new Endpoint("192.168.101.101", 8080, 2));
        endpoints.add(new Endpoint("192.168.101.102", 8080, 5));
        endpoints.add(new Endpoint("192.168.101.103", 8080, 7));
        return endpoints;
    }

    static class RequestSender extends Thread {
        private static long id = -1;

        public RequestSender() {

        }

        static synchronized long nextId() {
            return ++id;
        }

        @Override
        public void run() {
            ServiceInvoker rd = ServiceInvoker.getInstance();

            for (int i = 0; i < 100; i++) {
                rd.dispatchRequest(new Request(nextId(), 1));
                Tools.randomPause(100);
            }
        }
    }

}