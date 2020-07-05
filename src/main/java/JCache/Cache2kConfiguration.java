package JCache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class Cache2kConfiguration {

    public CacheManager init() {
        CachingProvider cachingProvider = Caching.getCachingProvider("org.cache2k.jcache.provider.JCacheProvider");
        return cachingProvider.getCacheManager();
    }

    public Cache configure(CacheManager manager, String cacheName) {

        MutableConfiguration<Integer, String> config
                = new MutableConfiguration<>();
        Cache<Integer, String> cache = manager.createCache(cacheName, config);
        return  cache;
    }
}
