import JCache.Cache2kConfiguration;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;

import static org.junit.Assert.assertEquals;

public class Cache2kConfigurationTest {

    @Test
    public void simplePutAndGet() {

        Cache2kConfiguration cache2kConfiguration = new Cache2kConfiguration();
        CacheManager cacheManager = cache2kConfiguration.init();
        Cache cache = cache2kConfiguration.configure(cacheManager, "marekCache1");
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertEquals("value2", cache.get("key2"));

        cacheManager.destroyCache("marekCache1");

    }
}
