import JCache.Cache2kConfiguration;
import JCache.Cache2kService;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Cache2kConfigurationTest {

    @Test
    public void simplePutAndGet() {

        Cache2kConfiguration cache2kConfiguration = new Cache2kConfiguration();
        CacheManager cacheManager = cache2kConfiguration.init();
        Cache cache = cache2kConfiguration.configure(cacheManager, "marekCache1");
        cache.put(100, "value1");
        cache.put(200, "value2");

        assertEquals("value2", cache.get(200));

        cacheManager.destroyCache("marekCache1");

    }

    @Test
    public void loadDataFromDb() {

        Cache2kConfiguration cache2kConfiguration = new Cache2kConfiguration();
        CacheManager cacheManager = cache2kConfiguration.init();
        Cache cache = cache2kConfiguration.configure(cacheManager, "cacheFromDb");

        Cache2kService cache2kService = new Cache2kService();
        Map<Integer, String> myDataFromDb = cache2kService.loadMyDataFromDb();
        cache.putAll(myDataFromDb);

        assertEquals("OK", cache.get(200));

    }
}
