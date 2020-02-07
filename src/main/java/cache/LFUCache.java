package cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LFUCache<K, V> implements Cache<K, V> {

    private int capacity;
    private Map<K, Integer> counts;
    private Map<K, V> cache;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        counts = new HashMap<>(capacity);
        cache = new HashMap<>(capacity);
    }

    @Override
    public V put(K key, V value) {
        computeFrequency(key);
        cache.put(key, value);
        if (size() > capacity) {
            removeOverload();
        }
        return get(key);
    }

    private void removeOverload() {
        Optional<Map.Entry<K, Integer>> minEntry = counts.entrySet().stream()
                .min(Comparator.comparing(entry -> entry.getValue()));
        K entryKey = minEntry.get().getKey();
        remove(entryKey);
    }

    private void computeFrequency(K key) {
        counts.compute(key, (k, count) -> count == null ? 1 : ++count);
    }

    @Override
    public V get(K key) {
        computeFrequency(key);
        return cache.get(key);
    }

    @Override
    public V remove(K key) {
        counts.remove(key);
        return cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }
}
