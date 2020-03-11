package CC2003_S30_HT6;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFactory<K, V> {
    public Map<K, V> getMap(String entry) {
        entry = entry.toLowerCase();
        if(entry.equals("1")) {
            System.out.println("UsingHashMap");
            return new HashMap<>();
        } else if (entry.equals("2")) {
            System.out.println("Using LinkedHashMap");
            return new LinkedHashMap<>();
        } else {
            System.out.println("Using TreeMap");
            return new TreeMap<>();
        }
    }
}
