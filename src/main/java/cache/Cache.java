package cache;

/**
 * Cache interface
 * @param <K> type of cache Key
 * @param <V> type of cache Value
 */
public interface Cache<K, V> {

    /**
     * Put value to cache by key
     * @param key
     * @param value
     * @return Cached value
     */
    V put(K key, V value);

    /**
     * Get value from cache by key
     * @param key
     * @return Value
     */
    V get(K key);

    /**
     * Remove value from cache by key
     * @param key
     * @return Removed value
     */
    V remove(K key);

    /**
     * @return cache size
     */
    int size();

}
