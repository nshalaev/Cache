package cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

public class LFUCache<K, V> extends HashMap<K, V> implements Cache<K, V> {

    private int capacity;
    private HashMap<K, Integer> counts;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        counts = new HashMap<>();
    }

    public V cache(K key, V value) {
        if (containsKey(key)) {
            put(key, value);
            return receive(key);
        }

        if (size() == capacity) {
            Optional<Entry<K, Integer>> minEntry = counts.entrySet().stream()
                    .min(Comparator.comparing(entry -> entry.getValue()));

            K entryKey = minEntry.get().getKey();

            delete(entryKey);
        }

        counts.put(key, 1);

        return put(key, value);
    }

    public V receive(K key) {
        if (containsKey(key)) {
            Integer count = counts.get(key);
            counts.put(key, ++count);
        }
        return get(key);
    }

    public V delete(K key) {
        counts.remove(key);
        return remove(key);
    }
}
