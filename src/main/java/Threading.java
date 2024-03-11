import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Threading {
    public static void main(String[] args) {
        Thread thread = new Thread();
        PingPong pingPong = new PingPong();
        Runnable ping = () -> {
            try {
                pingPong.ping();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable pong = () -> {
            try {
                pingPong.pong();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread pingThread = new Thread(ping);
        Thread pongThread = new Thread(pong);
        pingThread.start();
        pongThread.start();
    }
}
