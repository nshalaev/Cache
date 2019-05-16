package cache;

import cache.factory.CacheFactory;
import cache.factory.EvictionStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LFUCacheTest {

    CacheFactory<String, Integer> cacheFactory;
    Cache<String, Integer> cache;

    @Before
    public void init(){
        cacheFactory = new CacheFactory<>();
        cache = cacheFactory.createCache(3, EvictionStrategy.LFU);

        cache.cache("key1", 1);
        cache.cache("key1", 1);
        cache.cache("key1", 1);
        cache.cache("key1", 1);
        cache.cache("key2", 2);
        cache.cache("key2", 2);
        cache.cache("key2", 2);
        cache.cache("key3", 3);
        cache.cache("key3", 3);
        cache.cache("key4", 4);
        cache.cache("key4", 4);
        cache.cache("key5", 5);

    }

    @Test
    public void receiveNull() {
        assertNull(cache.receive("key3"));
        assertNull(cache.receive("key4"));
    }

    @Test
    public void receiveMostFrequently(){
        assertEquals(Integer.valueOf(1), cache.receive("key1"));
    }

    @Test
    public void receiveLast() {
        assertEquals(Integer.valueOf(5), cache.receive("key5"));
    }

    @Test
    public void removeElement() {
        cache.delete("key5");
        assertNull(cache.receive("key5"));
    }
}
