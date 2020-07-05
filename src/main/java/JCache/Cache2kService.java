package JCache;

import java.util.HashMap;
import java.util.Map;

public class Cache2kService {

    public Map<Integer, String> loadMyDataFromDb() {

        Map<Integer, String> mapHttpErrors = new HashMap<>();
        mapHttpErrors.put(400, "Bad Request");
        mapHttpErrors.put(304, "Not Modified");
        mapHttpErrors.put(200, "OK");
        mapHttpErrors.put(301, "Moved Permanently");
        mapHttpErrors.put(500, "Internal Server Error");
        return mapHttpErrors;
    }
}
