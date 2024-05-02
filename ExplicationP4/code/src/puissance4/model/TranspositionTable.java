package puissance4.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TranspositionTable {
    private static final int MAX_ENTRIES = 5;
    public LinkedHashMap<String, List<Integer>> table;

    public TranspositionTable() {
        this.table = new LinkedHashMap<String, List<Integer>>(MAX_ENTRIES, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, List<Integer>> eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    }

    public void put(String key, List<Integer> val) {
        this.table.put(key, val);
    };

    public List<Integer> getVal(String key) {
        System.out.println('F');
        if (this.table.containsKey(key)) {
            System.out.println("FEUR");
            return this.table.get(key);
        } else {
            System.out.println("ZZZZZ");
            return null;
        }

    }

    public int size() {
        return this.table.size();
    }

    public int getDepth(String key) {
        return getVal(key).get(0);
    }

    public int getValue(String key) {
        return getVal(key).get(1);

    }

    public int getFlag(String key) {
        return getVal(key).get(2); // 0 = exact , -1 = Lower , 1 = Upper
    }

}
