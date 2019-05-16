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
     * @return Old value
     */
    V cache(K key, V value);

    /**
     * Get value from cache by key
     * @param key
     * @return Value
     */
    V receive(K key);

    /**
     * Remove value from cache by key
     * @param key
     * @return Removed value
     */
    V delete(K key);

}
