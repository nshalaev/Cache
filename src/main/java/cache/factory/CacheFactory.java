package cache.factory;

import cache.Cache;
import cache.LFUCache;
import cache.LRUCache;

/**
 * Cache factory
 * @param <K> type of cache Key
 * @param <V> type of cache Value
 */
public class CacheFactory<K, V> {

    /**
     * @param capacity of cache
     * @param strategy of cache
     * @return created cache
     * @throws IllegalStateException when eviction strategy is not supported
     */
    public Cache<K, V> createCache(int capacity, EvictionStrategy strategy){
        switch (strategy) {
            case LFU:
                return new LFUCache<>(capacity);
            case LRU: default:
                return new LRUCache<>(capacity);
        }
    }
}
