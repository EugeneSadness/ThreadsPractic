import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DelWriteReentrantLock implements Runnable{
    List<Integer> list;
    private final Lock lock = new ReentrantLock();

    public DelWriteReentrantLock(List<Integer> list){
        this.list = list;
    }

    @Override
    public void run() {

    }
}
