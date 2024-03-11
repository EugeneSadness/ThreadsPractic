import num11.FormatThread;
import num11.Formatter;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadingTest {

    @Test
    public void num1() {
    }

    @Test
    public void num2() throws InterruptedException {
        num2 thread = new num2();
        thread.start();
        thread.join();
        assertFalse(thread.isAlive());
    }

    @Test
    public void num3() throws InterruptedException {
        num33 thread1 = new num33();
        num32 thread2 = new num32();
        num31 thread3 = new num31();
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        assertFalse(thread1.isAlive());
        assertFalse(thread2.isAlive());
        assertFalse(thread3.isAlive());
    }

    @Test
    public void num4() throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        Runnable wTask = new WriterThread(list);
        Runnable dTask = new DeleterThread(list);
        Thread wThread = new Thread(wTask);
        Thread dThread = new Thread(dTask);
        dThread.start();
        wThread.start();
        wThread.join();
        dThread.join();
        assertEquals(0, list.size());
    }

    @Test
    public void num5() throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        DelWriteThread delWriteThread = new DelWriteThread(list);
        Thread dwThread2 = new Thread(delWriteThread);
        Thread dwThread1 = new Thread(delWriteThread);
        dwThread2.start();
        dwThread1.start();
        dwThread1.join();
        dwThread2.join();
        assertTrue(list.isEmpty());
    }

    @Test
    public void num6() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        List<Integer> synchList = Collections.synchronizedList(list);
        Runnable wTask = new WriterThread(synchList);
        Runnable dTask = new DeleterThread(synchList);
        Thread wThread = new Thread(wTask);
        Thread dThread = new Thread(dTask);
        dThread.start();
        wThread.start();
        wThread.join();
        dThread.join();
        assertEquals(0, list.size());
    }

    @Test
    public void num7() {
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

    @Test
    public void num8() {
        ArrayList<Integer> list = new ArrayList<>();
        Lock lock = new ReentrantLock();
        Random random = new Random();
        Thread addThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                try {
                    list.add(random.nextInt());
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread removeThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                try {
                    if (!list.isEmpty()) {
                        int index = random.nextInt(list.size());
                        list.remove(index);
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        removeThread.start();
        addThread.start();
        assertEquals(0, list.size());
    }

    @Test
    public void num9() {
        Lock lock = new ReentrantLock();
        Condition pingTurn = lock.newCondition();
        Condition pongTurn = lock.newCondition();
        AtomicBoolean isPingTurn = new AtomicBoolean(true);
        Thread ping = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (!isPingTurn.get()) {
                        pongTurn.signal();
                        pingTurn.await();
                    }
                    System.out.println("ping");
                    isPingTurn.set(false);
                    pongTurn.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread pong = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (isPingTurn.get()) {
                        pingTurn.signal();
                        pongTurn.await();
                    }
                    System.out.println("pong");
                    isPingTurn.set(true);
                    pingTurn.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });

        pong.start();
        ping.start();
    }

    @Test
    public void num10() throws InterruptedException {
        ConcurrentHashFuck<Integer, String> map = new ConcurrentHashFuck<>();
        Thread[] writeThreads = new Thread[5];
        Thread[] readThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            Integer finalI = i;
            writeThreads[i] = new Thread(() -> {
                map.put(finalI, "fuck" + finalI);
                System.out.println("Map size: " + map.size());
            });
            writeThreads[i].start();
        }

        for(int i = 0; i < 5; i++){
            writeThreads[i].join();
        }

        for (int i = 0; i < 5; i++) {
            Integer finalI = i;

            readThreads[i] = new Thread(() -> {
                System.out.println(map.get(finalI));
            });
            readThreads[i].start();
        }
    }

    @Test
    public void num11() throws InterruptedException {
        Formatter formatter = new Formatter();
        FormatThread[] threads = new FormatThread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new FormatThread(new Date(124, 5 + i, 5 - i, 5 - i, 5, 5), formatter);
            threads[i].start();
        }

        for (int i = 0; i < 5; i++) {
            threads[i].join();
            System.out.println(threads[i].getDateFormatted());
        }
    }
}