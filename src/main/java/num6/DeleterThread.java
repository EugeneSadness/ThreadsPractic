package num6;

import java.util.List;
import java.util.Random;

public class DeleterThread extends Thread {

    List<Integer> list;

    public DeleterThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        int count = 0;
        Random random = new Random();
        while (count < 10000) {
            synchronized (list) { //одновременная работа!
                if (!list.isEmpty()) {
                    count++;
                    System.out.println("Удаляю " + count);
                    list.remove(random.nextInt(list.size()));
                }
                else {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
