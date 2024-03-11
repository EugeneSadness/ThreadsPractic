import java.util.List;
import java.util.Random;

public class WriterSynchThread extends Thread {
    List<Integer> list;

    public WriterSynchThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        int count = 0;
        Random random = new Random();
        while (count < 10000) {
            count++;
            System.out.println("Записываю " + count);
            list.add(random.nextInt());
            list.notify();
        }
    }
}