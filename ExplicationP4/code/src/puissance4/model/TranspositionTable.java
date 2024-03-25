package puissance4.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TranspositionTable {
    private static final int MAX_ENTRIES = 5;
    public LinkedHashMap<Integer, Integer> table;

    public TranspositionTable() {
        this.table = new LinkedHashMap<Integer, Integer>(MAX_ENTRIES, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    }

    public void put(int key, int val) {
        this.table.put(key, val);
    };

    public Integer get(int key) {
        if (this.table.containsKey(key)) {
            return this.table.get(key);
        } else {
            return null;
        }

    }

    public int size() {
        return this.table.size();
    }

}
