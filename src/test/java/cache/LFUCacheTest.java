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

        cache.put("key1", 1);
        cache.put("key1", 1);
        cache.put("key1", 1);
        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key2", 2);
        cache.put("key2", 2);
        cache.put("key3", 3);
        cache.put("key3", 3);
        cache.put("key4", 4);
        cache.put("key4", 4);
        cache.put("key5", 5);

    }

    @Test
    public void receiveNull() {
        assertNull(cache.get("key3"));
        assertNull(cache.get("key4"));
    }

    @Test
    public void receiveMostFrequently(){
        assertEquals(Integer.valueOf(1), cache.get("key1"));
    }

    @Test
    public void receiveLast() {
        assertEquals(Integer.valueOf(5), cache.get("key5"));
    }

    @Test
    public void removeElement() {
        cache.remove("key5");
        assertNull(cache.get("key5"));
    }
}
