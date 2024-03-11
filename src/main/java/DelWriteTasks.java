import java.util.List;
import java.util.Random;

public class DelWriteTasks {

    public void put(List<Integer> list) throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            System.out.println("Заполняю.." + i);
            list.add(random.nextInt());
        }
    }

    public void del(List<Integer> list) throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            System.out.println("Удаляю.." + i);
            list.remove(random.nextInt(list.size()));
        }

    }
}
