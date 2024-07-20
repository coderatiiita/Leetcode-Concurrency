import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class H2OCyclicBarrier {
    private CyclicBarrier barrier;
    private Semaphore oxygen;
    private Semaphore hydrogen;

    public H2OCyclicBarrier() {
        barrier = new CyclicBarrier(3);
        oxygen = new Semaphore(1);
        hydrogen = new Semaphore(2);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogen.acquire();
        try {
            barrier.await();
        } catch (Exception ex) {
        }
        releaseHydrogen.run();
        hydrogen.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire();
        try {
            barrier.await();
        } catch (Exception ex) {
        }
        releaseOxygen.run();
        oxygen.release();
    }
}