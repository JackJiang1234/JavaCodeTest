package concurrency.base.lock.loadbalancer;

public interface LoadBalancer {
    void updateCandidate(final Candidate candidate);
    Endpoint nextEndpoint();
}
