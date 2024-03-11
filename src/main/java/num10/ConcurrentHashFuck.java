package num10;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentHashFuck<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void put(K key, V value) {
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public void remove(int n) {
        readLock.lock();
        try {
            map.remove(n);
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        writeLock.lock();
        try {
            return map.size();
        } finally {
            writeLock.unlock();
        }
    }


    // По поводу вопроса о том, будет ли эта реализация хуже ConcurrentHashMap.
    // Важно отметить, что ConcurrentHashMap - это высокооптимизированная реализация,
    // специально предназначенная для эффективной работы в многопоточной среде.
    // Она использует сложные алгоритмы и оптимизации для обеспечения безопасности
    // и производительности в многопоточной среде.
    //
    // С другой стороны, решение, использующее ReadWriteLock,
    // не столь оптимизировано как ConcurrentHashMap.
    // Использование ReadWriteLock обеспечивает параллельный доступ к данным для чтения
    // , но имеет свои накладные расходы на управление блокировками,
    // особенно при выполнении операций записи. В ситуациях с высокой степенью конкуренции
    // и большим количеством записей это может привести к снижению производительности.
    //
    // Таким образом, хотя реализация, использующая ReadWriteLock,
    // обеспечит параллельный доступ для чтения, она, вероятно,
    // будет хуже ConcurrentHashMap в плане общей производительности
    // и масштабируемости в высоконагруженных многопоточных сценариях записи.
}