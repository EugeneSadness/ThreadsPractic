import java.util.List;
import java.util.Random;

public class DelWriteThread extends Thread {

    List<Integer> list;
    public DelWriteThread(List<Integer> list) {
        this.list = list;
    }

    private synchronized void handle(){
        Random random = new Random();
        if(!list.isEmpty())
            list.remove(random.nextInt(list.size()));
        else
            list.add(random.nextInt());
    }

    @Override
    public void run() {
        for(int i = 0; i < 10000; i++){
            handle();
            System.out.println("Handling " + i);
        }
    }
}
