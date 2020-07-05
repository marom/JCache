import JCache.Cache2kConfiguration;
import JCache.Cache2kService;
import JCache.SimpleCacheLoader;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.integration.CacheLoader;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
    public void loadDataFromDbDirectlyIntoCache() {

        Cache2kConfiguration cache2kConfiguration = new Cache2kConfiguration();
        CacheManager cacheManager = cache2kConfiguration.init();
        Cache cache = cache2kConfiguration.configure(cacheManager, "cacheFromDb");

        Cache2kService cache2kService = new Cache2kService();
        Map<Integer, String> myDataFromDb = cache2kService.loadMyDataFromDb();
        cache.putAll(myDataFromDb);

        assertEquals("OK", cache.get(200));
        cacheManager.destroyCache("cacheFromDb");
    }

    @Test
    public void readThroughSimpleCaseWhenKeyDoesNotExistCache() {

        Cache2kConfiguration cache2kConfiguration = new Cache2kConfiguration();
        CacheManager cacheManager = cache2kConfiguration.init();

        CompleteConfiguration<Integer, String> configuration = new MutableConfiguration<Integer, String>()
                .setStoreByValue(false) // avoid local serialization
                .setReadThrough(true) // use CacheLoader when an entry is missing
                .setCacheLoaderFactory(
                        new FactoryBuilder.SingletonFactory<CacheLoader<Integer, String>>(
                                new SimpleCacheLoader(new Cache2kService())
                        )
                );
        Cache<Integer, String> cache = cacheManager.createCache("readThrough", configuration);

        assertFalse(cache.containsKey(400));
        assertEquals("Bad Request", cache.get(400));
        cacheManager.destroyCache("readThrough");
    }

}
