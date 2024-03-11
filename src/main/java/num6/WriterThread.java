package num6;

import java.util.List;
import java.util.Random;

public class WriterThread extends Thread {
    List<Integer> list;

    public WriterThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        int count = 0;
        Random random = new Random();
        while (count < 10000) {
            synchronized (list) { //одновременная работа!
                count++;
                System.out.println("Записываю " + count);
                list.add(random.nextInt());
                list.notify();
            }
        }
    }
}
