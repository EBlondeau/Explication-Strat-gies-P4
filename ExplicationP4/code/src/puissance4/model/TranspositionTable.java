package puissance4.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TranspositionTable {
    private static final int MAX_ENTRIES = 2000;
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
        if (this.table.containsKey(key)) {
            return this.table.get(key);
        } else {
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

    public String FindFirstEntryWithArrayMethod() {
        if (this.size() != 0) {
            return String.valueOf(this.table.entrySet().toArray()[0]);
        } else {
            return null;
        }
    }

    public void toXML() {
        LinkedHashMap yourData = this.table;
        XStream xstream = new XStream();
        String yourXML = xstream.toXml(yourData);
    }

}
