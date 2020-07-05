package JCache;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;
import java.util.HashMap;
import java.util.Map;

public class SimpleCacheLoader implements CacheLoader<Integer, String> {

    private Cache2kService cache2kService;

    public SimpleCacheLoader(Cache2kService cache2kService) {
        this.cache2kService = cache2kService;
    }

    @Override
    public String load(Integer key) throws CacheLoaderException {
        return cache2kService.loadMyDataFromDb().get(key);
    }

    @Override
    public Map<Integer, String> loadAll(Iterable<? extends Integer> keys) throws CacheLoaderException {
        final Map<Integer, String> loaded = new HashMap<Integer, String>();
        for (final Integer k : keys) {
            final String value = load(k);
            if (value != null) {
                loaded.put(k, value);
            }
        }
        return loaded;
    }
}
