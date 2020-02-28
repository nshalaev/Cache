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
        if (!cache.containsKey(key) && size() == capacity) {
            removeOverload();
        }
        computeFrequency(key);
        return cache.put(key, value);
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
        V value = cache.get(key);
        if (value != null) {
            computeFrequency(key);
        }
        return value;
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
