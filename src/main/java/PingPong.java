public class PingPong{

    public synchronized void ping() throws InterruptedException {
        boolean turn = true;
        while (true) {
            if (!turn) {
                turn = true;
                wait();
            }
            turn = false;
            System.out.println("ping");
            notify();
        }
    }

    public synchronized void pong() throws InterruptedException {
        boolean turn = true;
        while (true) {
            if (!turn) {
                turn = true;
                wait();
            }
            turn = false;
            System.out.println("pong");
            notify();
        }
    }
}
