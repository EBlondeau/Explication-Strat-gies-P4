package puissance4.model;

import java.util.HashMap;

public class TranspositionTable {

    public HashMap<Integer, Integer> table;

    public TranspositionTable() {
    }

    public void put(int key, int val) {
        this.table.put(key, val);
    };

    public int get(int key) {
        return this.table.get(key);

    }
}
