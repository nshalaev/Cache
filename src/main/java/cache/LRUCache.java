package cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> implements Cache<K, V> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public V cache(K key, V value) {
        return put(key, value);
    }

    public V receive(K key) {
        return get(key);
    }

    public V delete(K key) {
        return remove(key);
    }
}