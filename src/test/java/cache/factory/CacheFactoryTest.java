package cache.factory;

import cache.Cache;
import cache.LFUCache;
import cache.LRUCache;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CacheFactoryTest {

    @Test
    public void createLFUCache() {
        CacheFactory cacheFactory = new CacheFactory();
        Cache cache = cacheFactory.createCache(1, EvictionStrategy.LFU);
        assertTrue(cache instanceof LFUCache);
    }

    @Test
    public void createLRUCache() {
        CacheFactory cacheFactory = new CacheFactory();
        Cache cache = cacheFactory.createCache(1, EvictionStrategy.LRU);
        assertTrue(cache instanceof LRUCache);
    }

    @Test
    public void getNullPointerException() {
        CacheFactory cacheFactory = new CacheFactory();
        Assert.assertThrows(NullPointerException.class,
                () -> cacheFactory.createCache(1, null));
    }

    @Test
    public void getIllegalArgumentException() {
        CacheFactory cacheFactory = new CacheFactory();
        Assert.assertThrows(IllegalArgumentException.class,
                () -> cacheFactory.createCache(-1, EvictionStrategy.LRU));
    }

}
