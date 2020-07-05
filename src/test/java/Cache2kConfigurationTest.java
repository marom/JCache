import junit.framework.TestCase;
import org.junit.Test;

import javax.cache.Cache;

import static org.junit.Assert.assertEquals;

public class Cache2kConfigurationTest {

    @Test
    public void simplePutAndGet() {

        Cache2kConfiguration cache2kConfiguration = new Cache2kConfiguration();

        Cache cache = cache2kConfiguration.configure();
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertEquals("value2", cache.get("key2"));

    }
}
