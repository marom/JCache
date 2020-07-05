package JCache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class Cache2kConfiguration {

    public Cache configure(String cacheName) {

        CachingProvider cachingProvider = Caching.getCachingProvider("org.cache2k.jcache.provider.JCacheProvider");
        CacheManager cacheManager = cachingProvider.getCacheManager();
        MutableConfiguration<String, String> config
                = new MutableConfiguration<>();
        Cache<String, String> cache = cacheManager.createCache(cacheName, config);
        return  cache;
    }
}
