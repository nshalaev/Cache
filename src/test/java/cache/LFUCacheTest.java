package cache;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LFUCacheTest {

    private Cache<String, Integer> cache;
    private int maxCapacity = 3;

    @Before
    public void init(){
        cache = new LFUCache<>(maxCapacity);
        cache.put("key1", 1);
        cache.get("key1");
        cache.get("key1");
        cache.get("key1");
        cache.put("key2", 2);
        cache.get("key2");
        cache.get("key2");
        cache.put("key3", 3);
        cache.get("key3");
        cache.put("key4", 4);
        cache.get("key4");
        cache.put("key5", 5);
    }

    @Test
    public void receiveNull() {
        assertNull(cache.get("key3"));
        assertNull(cache.get("key4"));
    }

    @Test
    public void receiveNotNull(){
        assertNotNull(cache.get("key1"));
        assertNotNull(cache.get("key2"));
        assertNotNull(cache.get("key5"));
    }

    @Test
    public void removeElement() {
        cache.remove("key5");
        assertNull(cache.get("key5"));
    }

    @Test
    public void getSize() {
        assertEquals(maxCapacity, cache.size());
    }
}
