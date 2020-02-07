package cache;

import cache.factory.CacheFactory;
import cache.factory.EvictionStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {

    Cache<String, Integer> cache;

    @Before
    public void init(){
        CacheFactory<String, Integer> cacheFactory = new CacheFactory<>();
        cache = cacheFactory.createCache(3, EvictionStrategy.LRU);
        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key3", 3);
        cache.get("key1");
        cache.put("key4", 4);
        cache.get("key1");
        cache.put("key5", 5);
        cache.put("key6", 6);
    }


    @Test
    public void receiveNull() {
        assertNull(cache.get("key2"));
        assertNull(cache.get("key3"));
        assertNull(cache.get("key4"));
    }

    @Test
    public void receiveNotNull() {
        assertNotNull(cache.get("key1"));
        assertNotNull(cache.get("key5"));
        assertNotNull(cache.get("key6"));
    }

    @Test
    public void removeElement() {
        cache.remove("key5");
        assertNull(cache.get("key5"));
    }

    @Test
    public void getSize() {
        assertEquals(3, cache.size());
    }

}
