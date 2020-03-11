import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * <h1>Main</h1>
 * Factory that generates a new map according to what the user selects in the Main class.
 * <p>
 *
 * @author Sebastian Gonzales (tabufellin) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-11
 **/
public class MapFactory<K, V> {
    /**
     * This method generates a map according to the user selection.
     *
     * @return Map with the selected map.
     */
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
