import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

public class H2OPhaser {
    private Phaser phaser;
    private Semaphore oxygen;
    private Semaphore hydrogen;

    public H2OPhaser() {
        phaser = new Phaser(3);
        oxygen = new Semaphore(1);
        hydrogen = new Semaphore(2);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogen.acquire();
        releaseHydrogen.run();
        phaser.arriveAndAwaitAdvance();
        hydrogen.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire();
        releaseOxygen.run();
        phaser.arriveAndAwaitAdvance();
        oxygen.release();
    }
}
