package num6;

import java.util.List;
import java.util.Random;

public class DeleterThread extends Thread {

    final List<Integer> list;

    public DeleterThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        int count = 0;
        Random random = new Random();
        while (count < 10000) {
            if (!list.isEmpty()) {
                count++;
                System.out.println("Удаляю " + count);
                list.remove(random.nextInt(list.size()));
            }
        }
    }
}
